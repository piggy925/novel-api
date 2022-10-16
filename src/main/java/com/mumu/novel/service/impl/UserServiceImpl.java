package com.mumu.novel.service.impl;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mumu.novel.core.common.constant.ErrorCodeEnum;
import com.mumu.novel.core.common.exception.BusinessException;
import com.mumu.novel.core.common.resp.RestResp;
import com.mumu.novel.core.constant.DatabaseConsts;
import com.mumu.novel.core.constant.SystemConfigConsts;
import com.mumu.novel.core.util.JwtUtils;
import com.mumu.novel.dao.entity.UserInfo;
import com.mumu.novel.dao.mapper.UserInfoMapper;
import com.mumu.novel.dto.req.UserLoginReqDto;
import com.mumu.novel.dto.req.UserRegisterReqDto;
import com.mumu.novel.dto.resp.UserLoginRespDto;
import com.mumu.novel.dto.resp.UserRegisterRespDto;
import com.mumu.novel.manager.cache.VerifyCodeManager;
import com.mumu.novel.service.UserService;

import lombok.RequiredArgsConstructor;

/**
 * 会员模块 服务类
 *
 * @author mumu
 * @date 2022/10/10
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final VerifyCodeManager verifyCodeManager;

    private final UserInfoMapper userInfoMapper;

    private final JwtUtils jwtUtils;

    /**
     * 用户注册
     */
    @Override
    public RestResp<UserRegisterRespDto> register(UserRegisterReqDto dto) {
        // 校验验证码是否正确
        if (!verifyCodeManager.isVerifyCodeValid(dto.getSessionId(), dto.getVelCode())) {
            throw new BusinessException(ErrorCodeEnum.USER_VERIFY_CODE_ERROR);
        }

        // 校验手机号是否已注册
        QueryWrapper<UserInfo> wrapper = new QueryWrapper();
        wrapper.eq(DatabaseConsts.UserInfoTable.COLUMN_USERNAME, dto.getUsername()).last(DatabaseConsts.SqlEnum.LIMIT_1.getSql());
        if (userInfoMapper.selectCount(wrapper) > 0) {
            throw new BusinessException(ErrorCodeEnum.USER_NAME_EXIST);
        }

        // 用户注册
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(dto.getUsername());
        userInfo.setNickName(dto.getUsername());
        userInfo.setPassword(DigestUtils.md5DigestAsHex(dto.getPassword().getBytes(StandardCharsets.UTF_8)));
        userInfo.setSalt("0");
        userInfo.setCreateTime(new Date());
        userInfo.setUpdateTime(new Date());
        userInfoMapper.insert(userInfo);

        // 删除验证码
        verifyCodeManager.removeImgVerifyCode(dto.getSessionId());

        // 生成 JWT 并返回
        Long id = userInfo.getId();
        String token = jwtUtils.genToken(id, SystemConfigConsts.NOVEL_FRONT_KEY);
        return RestResp.ok(UserRegisterRespDto.builder().uid(id).token(token).build());
    }

    /**
     * 用户登录
     */
    @Override
    public RestResp<UserLoginRespDto> login(UserLoginReqDto dto) {
        // 查询用户信息
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.eq(DatabaseConsts.UserInfoTable.COLUMN_USERNAME, dto.getUsername()).last(DatabaseConsts.SqlEnum.LIMIT_1.getSql());
        UserInfo userInfo = userInfoMapper.selectOne(wrapper);
        if (Objects.isNull(userInfo)) {
            throw new BusinessException(ErrorCodeEnum.USER_ACCOUNT_NOT_EXIST);
        }

        // 判断密码是否正确
        String encryptPassword = DigestUtils.md5DigestAsHex(dto.getPassword().getBytes(StandardCharsets.UTF_8));
        if (!Objects.equals(userInfo.getPassword(), encryptPassword)) {
            throw new BusinessException(ErrorCodeEnum.USER_PASSWORD_ERROR);
        }

        // 生成 token 并返回
        Long uid = userInfo.getId();
        String token = jwtUtils.genToken(uid, SystemConfigConsts.NOVEL_FRONT_KEY);
        return RestResp.ok(UserLoginRespDto.builder()
            .uid(uid)
            .nickName(userInfo.getNickName())
            .token(token)
            .build());
    }

}
