package com.mumu.novel.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mumu.novel.core.common.constant.ApiRouterConsts;
import com.mumu.novel.core.common.resp.RestResp;
import com.mumu.novel.dto.resp.HomeBookRespDto;
import com.mumu.novel.service.HomeService;

import lombok.RequiredArgsConstructor;

/**
 * API 接口 - 首页模块
 *
 * @author mumu
 * @date 2022/10/08
 */
@RequiredArgsConstructor
@RestController
@RequestMapping(ApiRouterConsts.API_FRONT_HOME_URL_PREFIX)
public class HomeController {

    private final HomeService homeService;

    @GetMapping("books")
    public RestResp<List<HomeBookRespDto>> listHomeBooks() {
        return homeService.listHomeBooks();
    }

}
