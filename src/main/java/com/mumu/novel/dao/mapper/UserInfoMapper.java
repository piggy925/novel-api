package com.mumu.novel.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mumu.novel.dao.entity.UserInfo;

/**
 * 用户信息 Mapper 接口
 *
 * @author mumu
 * @date 2022/10/07
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {

}
