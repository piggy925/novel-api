package com.mumu.novel.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mumu.novel.dao.entity.BookCommentReply;

/**
 * 小说评论回复 Mapper 接口
 *
 * @author mumu
 * @date 2022/10/07
 */
@Mapper
public interface BookCommentReplyMapper extends BaseMapper<BookCommentReply> {

}
