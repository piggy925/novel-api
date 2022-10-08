package com.mumu.novel.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mumu.novel.core.common.resp.RestResp;
import com.mumu.novel.dto.resp.HomeBookRespDto;
import com.mumu.novel.manager.HomeBookCacheManager;
import com.mumu.novel.service.HomeService;

import lombok.RequiredArgsConstructor;

/**
 * 首页模块 服务实现类
 *
 * @author mumu
 * @date 2022/10/09
 */
@Service
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService {

    private final HomeBookCacheManager homeBookCacheManager;
    
    @Override
    public RestResp<List<HomeBookRespDto>> listHomeBooks() {
        return RestResp.ok(homeBookCacheManager.listHomeBooks());
    }
    
}
