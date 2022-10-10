package com.mumu.novel.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mumu.novel.core.common.resp.RestResp;
import com.mumu.novel.core.constant.ApiRouterConsts;
import com.mumu.novel.core.constant.SystemConfigConsts;
import com.mumu.novel.dto.req.UserRegisterReqDto;
import com.mumu.novel.dto.resp.UserRegisterRespDto;
import com.mumu.novel.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * 前台门户 - 用户模块
 *
 * @author mumu
 * @date 2022/10/10
 */
@Tag(name = "UserController", description = "前台门户-会员模块")
@SecurityRequirement(name = SystemConfigConsts.HTTP_AUTH_HEADER_NAME)
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiRouterConsts.API_FRONT_USER_URL_PREFIX)
public class UserController {

    private final UserService userService;

    /**
     * 用户注册接口
     */
    @Operation(summary = "用户注册接口")
    @PostMapping("register")
    public RestResp<UserRegisterRespDto> register(@Valid @RequestBody UserRegisterReqDto dto) {
        return userService.register(dto);
    }
}
