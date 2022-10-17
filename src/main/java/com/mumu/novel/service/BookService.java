package com.mumu.novel.service;

import com.mumu.novel.core.common.resp.RestResp;
import com.mumu.novel.dto.req.UserCommentReqDto;
import com.mumu.novel.dto.resp.BookCommentRespDto;
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

    /**
     * 小说评论查询接口
     */
    RestResp<BookCommentRespDto> listLatestComments(Long id);

    /**
     * 发表小说评论
     */
    RestResp<Void> addComment(UserCommentReqDto dto);

    /**
     * 修改小说评论
     */
    RestResp<Void> updateComment(Long id, String content);

    /**
     * 添加小说评论
     */
    RestResp<Void> deleteComment(Long id);
}
