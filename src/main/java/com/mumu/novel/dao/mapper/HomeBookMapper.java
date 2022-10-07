package com.mumu.novel.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mumu.novel.dao.entity.HomeBook;

/**
 * 小说推荐 Mapper 接口
 *
 * @author mumu
 * @date 2022/05/12
 */
@Mapper
public interface HomeBookMapper extends BaseMapper<HomeBook> {

}
