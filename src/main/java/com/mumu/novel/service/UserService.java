package com.mumu.novel.service;

import com.mumu.novel.core.common.resp.RestResp;
import com.mumu.novel.dto.req.UserRegisterReqDto;
import com.mumu.novel.dto.resp.UserRegisterRespDto;

/**
 * 会员模块 服务类
 * 
 * @author mumu
 * @date 2022/10/10
 */
public interface UserService {

    /**
     * 用户注册
     */
    RestResp<UserRegisterRespDto> register(UserRegisterReqDto dto);
    
}
