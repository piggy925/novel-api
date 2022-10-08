package com.mumu.novel.service;

import java.util.List;

import com.mumu.novel.core.common.resp.RestResp;
import com.mumu.novel.dto.resp.HomeBookRespDto;

/**
 * 首页模块 服务类
 *
 * @author mumu
 * @date 2022/10/09
 */
public interface HomeService {

    /**
     * 查询首页小说推荐列表
     *
     * @return 首页小说推荐列表的 rest 响应结果
     */
    RestResp<List<HomeBookRespDto>> listHomeBooks();
}
