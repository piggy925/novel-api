package com.mumu.novel.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mumu.novel.core.common.constant.ApiRouterConsts;
import com.mumu.novel.core.common.resp.RestResp;
import com.mumu.novel.dto.resp.ImgVerifyCodeRespDto;
import com.mumu.novel.service.ResourceService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * @author mumu
 * @date 2022/10/10
 */
@Tag(name = "ResourceController", description = "前台门户-资源模块")
@RequiredArgsConstructor
@RestController
@RequestMapping(ApiRouterConsts.API_FRONT_RESOURCE_URL_PREFIX)
public class ResourceController {

    private final ResourceService resourceService;

    @GetMapping("img_verify_code")
    public RestResp<ImgVerifyCodeRespDto> getImgVerifyCode() throws IOException {
        return resourceService.getImgVerifyCode();
    }

}
