package com.mumu.novel.manager;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mumu.novel.core.common.constant.CacheConsts;
import com.mumu.novel.dao.entity.BookInfo;
import com.mumu.novel.dao.entity.HomeBook;
import com.mumu.novel.dao.mapper.BookInfoMapper;
import com.mumu.novel.dao.mapper.HomeBookMapper;
import com.mumu.novel.dto.resp.HomeBookRespDto;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;

/**
 * 缓存管理类 - 首页推荐小说
 *
 * @author mumu
 * @date 2022/10/08
 */
@Component
@RequiredArgsConstructor
public class HomeBookCacheManager {

    @Resource
    private final HomeBookMapper homeBookMapper;

    private final BookInfoMapper bookInfoMapper;

    @Cacheable(cacheManager = CacheConsts.CAFFEINE_CACHE_MANAGER, value = CacheConsts.HOME_BOOK_CACHE_NAME)
    public List<HomeBookRespDto> listHomeBooks() {
        List<HomeBook> homeBooks = homeBookMapper.selectList(null);
        if (!CollectionUtils.isEmpty(homeBooks)) {
            // 获取首页书籍id集合
            List<Long> bookIds = homeBooks.stream().map(HomeBook::getBookId).toList();
            // 通过id查询书籍详细信息
            QueryWrapper<BookInfo> bookInfoQueryWrapper = new QueryWrapper<>();
            bookInfoQueryWrapper.in("id", bookIds);
            List<BookInfo> bookInfos = bookInfoMapper.selectList(bookInfoQueryWrapper);

            if (!CollectionUtils.isEmpty(bookInfos)) {
                Map<Long, BookInfo> bookInfoMap = bookInfos.stream().collect(Collectors.toMap(BookInfo::getId, Function.identity()));
                return homeBooks.stream().map(v -> {
                    BookInfo bookInfo = bookInfoMap.get(v.getBookId());
                    HomeBookRespDto homeBookRespDto = new HomeBookRespDto();
                    homeBookRespDto.setBookId(bookInfo.getId());
                    homeBookRespDto.setPicUrl(bookInfo.getPicUrl());
                    homeBookRespDto.setBookName(bookInfo.getBookName());
                    homeBookRespDto.setAuthorName(bookInfo.getAuthorName());
                    homeBookRespDto.setBookDesc(bookInfo.getBookDesc());
                    return homeBookRespDto;
                }).toList();
            }
        }
        return Collections.emptyList();
    }
}
