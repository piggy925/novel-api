package com.mumu.novel.service;

import com.mumu.novel.core.common.resp.RestResp;
import com.mumu.novel.dto.resp.BookInfoRespDto;

/**
 * 小说模块 服务类
 * 
 * @author mumu
 * @date 2022/10/14
 */
public interface BookService {

    /**
     * 小说信息查询接口
     */
    RestResp<BookInfoRespDto> getBookById(Long id);

}
