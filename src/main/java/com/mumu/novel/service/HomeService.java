package com.mumu.novel.service;

import java.util.List;

import com.mumu.novel.core.common.resp.RestResp;
import com.mumu.novel.dto.resp.HomeBookRespDto;

/**
 * 首页模块
 *
 * @author mumu
 * @date 2022/10/08
 */
public interface HomeService {
    /**
     * 查询首页推荐小说
     */
    RestResp<List<HomeBookRespDto>> listHomeBooks();
}
