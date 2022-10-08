package com.mumu.novel.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mumu.novel.core.common.resp.RestResp;
import com.mumu.novel.dto.resp.HomeBookRespDto;
import com.mumu.novel.manager.HomeBookCacheManager;
import com.mumu.novel.service.HomeService;

import lombok.RequiredArgsConstructor;

/**
 * @author mumu
 * @date 2022/10/08
 */
@Service
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService {

    private final HomeBookCacheManager homeBookCacheManager;

    /**
     * 查询首页推荐小说
     */
    @Override
    public RestResp<List<HomeBookRespDto>> listHomeBooks() {
        return RestResp.ok(homeBookCacheManager.listHomeBooks());
    }

}
