package com.mumu.novel.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mumu.novel.core.common.resp.RestResp;
import com.mumu.novel.core.constant.DatabaseConsts;
import com.mumu.novel.dao.entity.BookComment;
import com.mumu.novel.dao.entity.UserInfo;
import com.mumu.novel.dao.mapper.BookCommentMapper;
import com.mumu.novel.dto.resp.BookCommentRespDto;
import com.mumu.novel.dto.resp.BookInfoRespDto;
import com.mumu.novel.manager.cache.BookInfoCacheManager;
import com.mumu.novel.manager.dao.UserDaoManager;
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

    private final BookCommentMapper bookCommentMapper;

    private final UserDaoManager userDaoManager;

    /**
     * 小说信息查询接口
     */
    @Override
    public RestResp<BookInfoRespDto> getBookById(Long id) {
        return RestResp.ok(bookInfoCacheManager.getBookInfo(id));
    }

    /**
     * 小说评论查询接口
     */
    @Override
    public RestResp<BookCommentRespDto> listLatestComments(Long id) {
        // 查询评论总数
        QueryWrapper<BookComment> commentCountQueryWrapper = new QueryWrapper<>();
        commentCountQueryWrapper.eq(DatabaseConsts.BookCommentTable.COLUMN_BOOK_ID, id);
        Long commentCount = bookCommentMapper.selectCount(commentCountQueryWrapper);
        BookCommentRespDto bookCommentRespDto = BookCommentRespDto.builder().commentTotal(commentCount).build();
        if (commentCount > 0) {
            // 查询最近的 5 条评论
            QueryWrapper<BookComment> commentQueryWrapper = new QueryWrapper<>();
            commentCountQueryWrapper.eq(DatabaseConsts.BookCommentTable.COLUMN_BOOK_ID, id)
                .orderByDesc(DatabaseConsts.CommonColumnEnum.CREATE_TIME.getName())
                .last(DatabaseConsts.SqlEnum.LIMIT_5.getSql());
            List<BookComment> bookComments = bookCommentMapper.selectList(commentQueryWrapper);

            // 查询评论中的用户信息
            List<Long> userIds = bookComments.stream().map(BookComment::getUserId).toList();
            List<UserInfo> userInfos = userDaoManager.listUsers(userIds);
            Map<Long, String> userInfoMap = userInfos.stream().collect(Collectors.toMap(UserInfo::getId,
                UserInfo::getNickName));
            List<BookCommentRespDto.CommentInfo> commentInfos = bookComments.stream()
                .map(v -> BookCommentRespDto.CommentInfo.builder()
                    .id(v.getId())
                    .commentUserId(v.getUserId())
                    .commentContent(v.getCommentContent())
                    .commentUser(userInfoMap.get(v.getId()))
                    .commentTime(v.getCreateTime())
                    .build()
                ).toList();
            bookCommentRespDto.setComments(commentInfos);
        } else {
            bookCommentRespDto.setComments(Collections.emptyList());
        }
        return RestResp.ok(bookCommentRespDto);
    }

}
