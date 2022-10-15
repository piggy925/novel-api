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
import com.mumu.novel.core.constant.CacheConsts;
import com.mumu.novel.core.constant.DatabaseConsts;
import com.mumu.novel.dao.entity.BookInfo;
import com.mumu.novel.dao.entity.HomeBook;
import com.mumu.novel.dao.mapper.BookInfoMapper;
import com.mumu.novel.dao.mapper.HomeBookMapper;
import com.mumu.novel.dto.resp.HomeBookRespDto;

import lombok.RequiredArgsConstructor;

/**
 * 首页推荐小说 缓存管理类
 *
 * @author mumu
 * @date 2022/10/08
 */
@Component
@RequiredArgsConstructor
public class HomeBookCacheManager {

    private final HomeBookMapper homeBookMapper;

    private final BookInfoMapper bookInfoMapper;

    /**
     * 查询首页小说推荐，并放入缓存中
     */
    @Cacheable(cacheManager = CacheConsts.CAFFEINE_CACHE_MANAGER, value = CacheConsts.HOME_BOOK_CACHE_NAME)
    public List<HomeBookRespDto> listHomeBooks() {
        // 从首页小说推荐表中查询出需要推荐的小说
        QueryWrapper<HomeBook> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc(DatabaseConsts.CommonColumnEnum.SORT.getName());
        List<HomeBook> homeBooks = homeBookMapper.selectList(queryWrapper);

        // 获取推荐小说ID列表
        if (!CollectionUtils.isEmpty(homeBooks)) {
            List<Long> bookIds = homeBooks.stream().map(HomeBook::getBookId).toList();

            // 根据小说ID列表查询相关的小说信息列表
            QueryWrapper<BookInfo> bookInfoQueryWrapper = new QueryWrapper<>();
            bookInfoQueryWrapper.in(DatabaseConsts.CommonColumnEnum.ID.getName(), bookIds);
            List<BookInfo> bookInfos = bookInfoMapper.selectList(bookInfoQueryWrapper);

            // 组装 HomeBookRespDto 列表数据并返回
            if (!CollectionUtils.isEmpty(bookInfos)) {
                Map<Long, BookInfo> bookInfoMap = bookInfos.stream().collect(Collectors.toMap(BookInfo::getId,
                    Function.identity()));
                return homeBooks.stream().map(v -> {
                    BookInfo bookInfo = bookInfoMap.get(v.getBookId());
                    HomeBookRespDto bookRespDto = new HomeBookRespDto();
                    bookRespDto.setType(v.getType());
                    bookRespDto.setBookId(v.getBookId());
                    bookRespDto.setBookName(bookInfo.getBookName());
                    bookRespDto.setPicUrl(bookInfo.getPicUrl());
                    bookRespDto.setAuthorName(bookInfo.getAuthorName());
                    bookRespDto.setBookDesc(bookInfo.getBookDesc());
                    return bookRespDto;
                }).toList();
            }
        }
        return Collections.emptyList();
    }

}
