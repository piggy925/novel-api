package com.mumu.novel.manager;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mumu.novel.core.constant.CacheConsts;
import com.mumu.novel.core.constant.DatabaseConsts;
import com.mumu.novel.dao.entity.BookChapter;
import com.mumu.novel.dao.entity.BookInfo;
import com.mumu.novel.dao.mapper.BookChapterMapper;
import com.mumu.novel.dao.mapper.BookInfoMapper;
import com.mumu.novel.dto.resp.BookInfoRespDto;

import lombok.RequiredArgsConstructor;

/**
 * 小说信息 - 缓存管理类
 *
 * @author mumu
 * @date 2022/10/14
 */
@Component
@RequiredArgsConstructor
public class BookInfoCacheManager {

    private final BookInfoMapper bookInfoMapper;

    private final BookChapterMapper bookChapterMapper;

    @Cacheable(cacheManager = CacheConsts.CAFFEINE_CACHE_MANAGER, value = CacheConsts.BOOK_INFO_CACHE_NAME)
    public BookInfoRespDto getBookInfo(Long id) {
        return cacheBookInfo(id);
    }

    @CachePut(cacheManager = CacheConsts.CAFFEINE_CACHE_MANAGER, value = CacheConsts.BOOK_INFO_CACHE_NAME)
    public BookInfoRespDto cacheBookInfo(Long id) {
        // 查询书籍信息
        BookInfo bookInfo = bookInfoMapper.selectById(id);
        // 查询首章节信息
        QueryWrapper<BookChapter> wrapper = new QueryWrapper<>();
        wrapper.eq(DatabaseConsts.BookChapterTable.COLUMN_BOOK_ID, id)
            .orderByAsc(DatabaseConsts.BookChapterTable.COLUMN_CHAPTER_NUM)
            .last(DatabaseConsts.SqlEnum.LIMIT_1.getSql());
        BookChapter firstBookChapter = bookChapterMapper.selectOne(wrapper);

        // 组装响应对象
        return BookInfoRespDto.builder()
            .id(bookInfo.getId())
            .bookName(bookInfo.getBookName())
            .bookDesc(bookInfo.getBookDesc())
            .bookStatus(bookInfo.getBookStatus())
            .authorId(bookInfo.getAuthorId())
            .authorName(bookInfo.getAuthorName())
            .categoryId(bookInfo.getCategoryId())
            .categoryName(bookInfo.getCategoryName())
            .commentCount(bookInfo.getCommentCount())
            .firstChapterId(firstBookChapter.getId())
            .lastChapterId(bookInfo.getLastChapterId())
            .picUrl(bookInfo.getPicUrl())
            .visitCount(bookInfo.getVisitCount())
            .wordCount(bookInfo.getWordCount())
            .build();
    }

}
