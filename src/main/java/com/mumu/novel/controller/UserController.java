package com.mumu.novel.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mumu.novel.core.auth.UserHolder;
import com.mumu.novel.core.common.resp.RestResp;
import com.mumu.novel.core.constant.ApiRouterConsts;
import com.mumu.novel.core.constant.SystemConfigConsts;
import com.mumu.novel.dto.req.UserCommentReqDto;
import com.mumu.novel.dto.req.UserLoginReqDto;
import com.mumu.novel.dto.req.UserRegisterReqDto;
import com.mumu.novel.dto.resp.UserLoginRespDto;
import com.mumu.novel.dto.resp.UserRegisterRespDto;
import com.mumu.novel.service.BookService;
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

    private final BookService bookService;

    /**
     * 用户注册接口
     */
    @Operation(summary = "用户注册接口")
    @PostMapping("register")
    public RestResp<UserRegisterRespDto> register(@Valid @RequestBody UserRegisterReqDto dto) {
        return userService.register(dto);
    }

    /**
     * 用户登录接口
     */
    @Operation(summary = "用户登录接口")
    @PostMapping("login")
    public RestResp<UserLoginRespDto> login(@Valid @RequestBody UserLoginReqDto dto) {
        return userService.login(dto);
    }
    
    /**
     * 添加评论接口
     */
    @Operation(summary = "小说评论添加接口")
    @PostMapping("comment")
    public RestResp<Void> addComment(@Valid @RequestBody UserCommentReqDto dto) {
        return bookService.addComment(dto);
    }

    /**
     * 修改评论接口
     */
    @Operation(summary = "小说评论修改接口")
    @PutMapping("comment/{id}")
    public RestResp<Void> updateComment(@PathVariable Long id, String content) {
        return bookService.updateComment(id, content);
    }

    /**
     * 删除评论接口
     */
    @Operation(summary = "小说评论删除接口")
    @DeleteMapping("comment/{id}")
    public RestResp<Void> deleteComment(@PathVariable Long id) {
        return bookService.deleteComment(id);
    }

}
