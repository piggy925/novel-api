package com.mumu.novel.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mumu.novel.core.common.resp.RestResp;
import com.mumu.novel.core.constant.CacheConsts;
import com.mumu.novel.core.constant.DatabaseConsts;
import com.mumu.novel.dao.entity.BookChapter;
import com.mumu.novel.dao.entity.BookComment;
import com.mumu.novel.dao.entity.UserInfo;
import com.mumu.novel.dao.mapper.BookChapterMapper;
import com.mumu.novel.dao.mapper.BookCommentMapper;
import com.mumu.novel.dto.req.UserCommentReqDto;
import com.mumu.novel.dto.resp.BookChapterAboutRespDto;
import com.mumu.novel.dto.resp.BookChapterRespDto;
import com.mumu.novel.dto.resp.BookCommentRespDto;
import com.mumu.novel.dto.resp.BookInfoRespDto;
import com.mumu.novel.manager.cache.BookChapterCacheManager;
import com.mumu.novel.manager.cache.BookContentCacheManager;
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

    private final BookChapterMapper bookChapterMapper;

    private final BookChapterCacheManager bookChapterCacheManager;

    private final BookContentCacheManager bookContentCacheManager;

    private final StringRedisTemplate stringRedisTemplate;

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
            commentQueryWrapper.eq(DatabaseConsts.BookCommentTable.COLUMN_BOOK_ID, id)
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
                    .commentUser(userInfoMap.get(v.getUserId()))
                    .commentTime(v.getCreateTime())
                    .build()
                ).toList();
            bookCommentRespDto.setComments(commentInfos);
        } else {
            bookCommentRespDto.setComments(Collections.emptyList());
        }
        return RestResp.ok(bookCommentRespDto);
    }

    /**
     * 发表小说评论
     */
    @Override
    public RestResp<Void> addComment(UserCommentReqDto dto) {
        // 此处可以添加评论相关限制，比如用户只能对同一本书每天评论三条等
        BookComment bookComment = new BookComment();
        bookComment.setUserId(Long.valueOf(stringRedisTemplate.opsForValue().get(CacheConsts.LOGIN_USER_ID_NAME)));
        bookComment.setBookId(dto.getBookId());
        bookComment.setCommentContent(dto.getCommentContent());
        bookCommentMapper.insert(bookComment);
        return RestResp.ok();
    }

    /**
     * 修改小说评论
     */
    @Override
    public RestResp<Void> updateComment(Long id, String content) {
        String userId = stringRedisTemplate.opsForValue().get(CacheConsts.LOGIN_USER_ID_NAME);
        QueryWrapper<BookComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(DatabaseConsts.BookCommentTable.COLUMN_USER_ID, userId)
            .eq(DatabaseConsts.CommonColumnEnum.ID.getName(), id);
        BookComment bookComment = new BookComment();
        bookComment.setCommentContent(content);
        bookCommentMapper.update(bookComment, queryWrapper);
        return RestResp.ok();
    }

    /**
     * 添加小说评论
     */
    @Override
    public RestResp<Void> deleteComment(Long id) {
        String userId = stringRedisTemplate.opsForValue().get(CacheConsts.LOGIN_USER_ID_NAME);
        QueryWrapper<BookComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(DatabaseConsts.BookCommentTable.COLUMN_USER_ID, userId)
            .eq(DatabaseConsts.CommonColumnEnum.ID.getName(), id);
        bookCommentMapper.delete(queryWrapper);
        return RestResp.ok();
    }

    /**
     * 查询小说章节信息
     */
    @Override
    public RestResp<BookChapterAboutRespDto> getLastChapterAbout(Long bookId) {
        // 查询小说信息
        BookInfoRespDto bookInfo = bookInfoCacheManager.getBookInfo(bookId);

        // 查询最新章节信息
        Long lastChapterId = bookInfo.getLastChapterId();
        BookChapterRespDto lastChapter = bookChapterCacheManager.getChapter(lastChapterId);

        // 查询章节内容
        String bookContent = bookContentCacheManager.getBookContent(lastChapterId);

        // 查询章节总数
        QueryWrapper<BookChapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(DatabaseConsts.BookChapterTable.COLUMN_BOOK_ID, bookId);
        Long chapterCount = bookChapterMapper.selectCount(queryWrapper);

        return RestResp.ok(BookChapterAboutRespDto.builder()
            .chapterInfo(lastChapter)
            .chapterTotal(chapterCount)
            .contentSummary(bookContent.substring(0, 100).trim().replaceAll("<br/>", ""))
            .build());
    }

}
