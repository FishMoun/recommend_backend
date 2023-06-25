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

        Integer userId = 2;
        try {
            // 调用 recommendByObject 方法进行基于用户的推荐
            BaseResponse<MovieInfo[]> response = recommendService.recommendByObject(userId);
            // 验证结果
            assertNotNull(response);
            assertEquals(200, response.getCode());
            assertNotNull(response.getData());
            assertEquals(5, response.getData().length);
            //输出推荐电影的id
            System.out.println("Recommended Movies:");
            for (MovieInfo movie : response.getData()) {
                System.out.println(movie.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //未对电影评价的用户
        Integer userId2 = 6042;
        try {
            // 调用 recommendByObject 方法进行基于用户的推荐
            BaseResponse<MovieInfo[]> response2 = recommendService.recommendByObject(userId2);
            // 验证结果
            assertNotNull(response2);
            assertEquals(200, response2.getCode());
            assertNotNull(response2.getData());
            assertEquals(5, response2.getData().length);
            assertEquals("该用户没有评价过电影，随机推荐！", response2.getMessage());
            //输出推荐电影的id
            System.out.println("Recommended Movies:");
            for (MovieInfo movie : response2.getData()) {
                System.out.println(movie.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void recommendByHighScore() {
        // 执行方法
        BaseResponse<MovieInfo[]> response = recommendService.recommendByHighScore();

        // 验证结果
        assertNotNull(response);
        assertEquals(200, response.getCode());
        assertNotNull(response.getData());
        assertEquals(5, response.getData().length);

        // 验证推荐电影的评分是否大于等于4
        for (MovieInfo movie : response.getData()) {
            assertTrue(movie.getAvgRate() >= 4);
        }

        // 输出推荐电影的id
        System.out.println("Recommended Movies:");
        for (MovieInfo movie : response.getData()) {
            System.out.println(movie.getId());
        }
    }
}