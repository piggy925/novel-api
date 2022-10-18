package com.mumu.novel.manager.cache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mumu.novel.core.constant.CacheConsts;
import com.mumu.novel.core.constant.DatabaseConsts;
import com.mumu.novel.dao.entity.BookContent;
import com.mumu.novel.dao.mapper.BookContentMapper;

import lombok.RequiredArgsConstructor;

/**
 * 小说内容缓存管理类
 *
 * @author mumu
 * @date 2022/10/18
 */
@Component
@RequiredArgsConstructor
public class BookContentCacheManager {

    private final BookContentMapper bookContentMapper;

    /**
     * 查询小说内容，并放入缓存中
     */
    @Cacheable(cacheManager = CacheConsts.REDIS_CACHE_MANAGER, value = CacheConsts.BOOK_CONTENT_CACHE_NAME)
    public String getBookContent(Long chapterId) {
        QueryWrapper<BookContent> contentQueryWrapper = new QueryWrapper<>();
        contentQueryWrapper.eq(DatabaseConsts.BookContentTable.COLUMN_CHAPTER_ID, chapterId)
            .last(DatabaseConsts.SqlEnum.LIMIT_1.getSql());
        BookContent bookContent = bookContentMapper.selectOne(contentQueryWrapper);
        return bookContent.getContent();
    }

}
