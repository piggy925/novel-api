package com.mumu.novel.manager.cache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.mumu.novel.core.constant.CacheConsts;
import com.mumu.novel.dao.entity.BookChapter;
import com.mumu.novel.dao.mapper.BookChapterMapper;
import com.mumu.novel.dto.resp.BookChapterRespDto;

import lombok.RequiredArgsConstructor;

/**
 * 小说章节缓存管理类
 *
 * @author mumu
 * @date 2022/10/18
 */
@Component
@RequiredArgsConstructor
public class BookChapterCacheManager {

    private final BookChapterMapper bookChapterMapper;

    @Cacheable(cacheManager = CacheConsts.CAFFEINE_CACHE_MANAGER, value = CacheConsts.BOOK_CHAPTER_CACHE_NAME)
    public BookChapterRespDto getChapter(Long chapterId) {
        BookChapter bookChapter = bookChapterMapper.selectById(chapterId);
        return BookChapterRespDto.builder()
            .id(chapterId)
            .bookId(bookChapter.getBookId())
            .chapterNum(bookChapter.getChapterNum())
            .chapterName(bookChapter.getChapterName())
            .chapterWordCount(bookChapter.getWordCount())
            .chapterUpdateTime(bookChapter.getUpdateTime())
            .build();
    }

}
