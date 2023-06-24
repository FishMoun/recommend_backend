package com.hhu.service.Impl;

import com.hhu.entity.MovieInfo;
import com.hhu.mapper.MovieMapper;
import com.hhu.mapper.RatingMapper;
import com.hhu.service.RateService;
import com.hhu.service.RecommendService;
import com.hhu.utils.BaseResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RecommendServiceImplTest {
    @Resource
    private RecommendService recommendService;

    @Test
    void recommendByObject() {
        // 假设 userId 为 123
        int userId = 123;

        try {
            // 调用 recommendByObject 方法进行基于用户的推荐
            BaseResponse<MovieInfo[]> result = recommendService.recommendByObject(userId);

            // 打印结果
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void recommendByHighScore() {
        // 调用 recommendByHighScore 方法进行高评分电影推荐
        BaseResponse<MovieInfo[]> result = recommendService.recommendByHighScore();

        // 打印结果
        System.out.println(result);
    }
}