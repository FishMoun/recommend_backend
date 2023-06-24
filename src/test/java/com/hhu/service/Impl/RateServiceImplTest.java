package com.hhu.service.Impl;

import com.hhu.entity.MovieInfo;
import com.hhu.entity.RatedMovie;
import com.hhu.entity.Rating;
import com.hhu.mapper.MovieMapper;
import com.hhu.mapper.RatingMapper;
import com.hhu.service.RateService;
import com.hhu.utils.BaseResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class RateServiceImplTest {
    @Resource
    private RateService rateService;

//    @Autowired
//    private MovieMapper movieMapper;
//
//    @Autowired             //实现依赖注入
//    private RatingMapper ratingMapper;


    @Test
    void rate() {
        // 创建 Rating 对象作为输入参数
        Rating rating= new Rating();
        rating.setUserId(123);
        rating.setMovieId(456);
        rating.setRating(5);

//        // 创建 MovieInfo 对象用于模拟数据库操作
//        MovieInfo movieInfo = new MovieInfo();
//        movieInfo.setId(456);
//        movieInfo.setAvgRate(4.5);
//        movieInfo.setRateNums(10);
//
//        // 模拟数据库操作的返回值
//        when(movieMapper.update(any(), any())).thenReturn(1);
//        when(ratingMapper.insert(any())).thenReturn(1);

        // 调用被测试的方法
        BaseResponse<Rating> result = rateService.rate(rating);

        // 断言结果是否符合预期
//        assertEquals(200, result.getCode());
//        assertEquals(rating, result.getData());

//        // 验证模拟的数据库操作是否被调用
//        verify(movieMapper, times(1)).update(any(), any());
//        verify(ratingMapper, times(1)).insert(any());


        // 打印结果
        System.out.println(result);
    }


    @Test
    void getRatedMovie() {
        // 假设 userId 为 123
        Integer userId = 123;

        // 调用 getRatedMovie 方法获取评分过的电影列表
        BaseResponse<RatedMovie[]> result = rateService.getRatedMovie(userId);

        // 打印结果
        System.out.println(result);

    }

    @Test
    void getMovieRateByUserId() {
        // 假设 userId 为 123，movieId 为 456
        Integer userId = 123;
        Integer movieId = 456;

        // 调用 getMovieRateByUserId 方法获取指定用户对指定电影的评分
        BaseResponse<Integer> result = rateService.getMovieRateByUserId(userId, movieId);

        // 打印结果
        System.out.println(result);
    }
}