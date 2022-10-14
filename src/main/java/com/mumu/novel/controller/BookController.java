package com.mumu.novel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mumu.novel.core.common.resp.RestResp;
import com.mumu.novel.core.constant.ApiRouterConsts;
import com.mumu.novel.dto.resp.BookInfoRespDto;
import com.mumu.novel.service.BookService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * API 接口 - 小说模块
 *
 * @author mumu
 * @date 2022/10/14
 */
@Tag(name = "BookController", description = "前台门户-小说模块")
@RestController
@RequestMapping(ApiRouterConsts.API_FRONT_BOOK_URL_PREFIX)
@RequiredArgsConstructor
public class BookController {

    private final BookService bookservice;

    @Operation(summary = "小说信息查询接口")
    @GetMapping("{id}")
    public RestResp<BookInfoRespDto> getBookById(@Parameter(description = "小说 ID") @PathVariable("id") Long id) {
        return bookservice.getBookById(id);
    }
}
