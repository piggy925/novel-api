package com.mumu.novel.service.impl;

import org.springframework.stereotype.Service;

import com.mumu.novel.core.common.resp.RestResp;
import com.mumu.novel.dto.resp.BookInfoRespDto;
import com.mumu.novel.manager.BookInfoCacheManager;
import com.mumu.novel.service.BookService;

import lombok.RequiredArgsConstructor;

/**
 * @author mumu
 * @date 2022/10/14
 */
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookInfoCacheManager bookInfoCacheManager;


    /**
     * 小说信息查询接口
     */
    @Override
    public RestResp<BookInfoRespDto> getBookById(Long id) {
        return RestResp.ok(bookInfoCacheManager.getBookInfo(id));
    }

}
