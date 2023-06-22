package com.hhu.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hhu.entity.Rating;
import com.hhu.entity.User;
import com.hhu.mapper.RatingMapper;
import com.hhu.service.RateService;
import com.hhu.utils.BaseResponse;
import com.hhu.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RateServiceImpl implements RateService {
    @Autowired
    private RatingMapper ratingMapper;
    @Override
    public BaseResponse<Rating> rate(Rating rating) {

        QueryWrapper<Rating> queryWrapper = new QueryWrapper<>();
        System.out.println(ratingMapper.insert(rating));
        return ResultUtils.success(rating);
    }
}
