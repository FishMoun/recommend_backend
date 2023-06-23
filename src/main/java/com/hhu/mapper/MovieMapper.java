package com.hhu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hhu.entity.MovieInfo;
import com.hhu.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MovieMapper extends BaseMapper<MovieInfo> {
}
