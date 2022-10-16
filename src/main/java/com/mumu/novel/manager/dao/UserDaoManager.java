package com.mumu.novel.manager.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mumu.novel.core.constant.DatabaseConsts;
import com.mumu.novel.dao.entity.UserInfo;
import com.mumu.novel.dao.mapper.UserInfoMapper;

import lombok.RequiredArgsConstructor;

/**
 * @author mumu
 * @date 2022/10/16
 */
@Component
@RequiredArgsConstructor
public class UserDaoManager {

    private final UserInfoMapper userInfoMapper;

    /**
     * 根据用户 id 集合批量查询用户信息列表
     */
    public List<UserInfo> listUsers(List<Long> userIds) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(DatabaseConsts.CommonColumnEnum.ID.getName(), userIds);
        return userInfoMapper.selectList(queryWrapper);
    }

}
