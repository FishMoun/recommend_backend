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

@SpringBootTest
class RateServiceImplTest {
    @Resource
    private RateService rateService;

    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private RatingMapper ratingMapper;

    @Test
    void rate() {
        // 1、构造测试数据
        Rating rating = new Rating();
        rating.setUserId(1);
        rating.setMovieId(3);
        rating.setRating(4);

        // 调用被测试的方法
        BaseResponse<Rating> response = rateService.rate(rating);

        // 验证返回结果
        assertEquals(200, response.getCode());
        assertEquals(rating, response.getData());

        // 验证数据库更新结果
        MovieInfo movieInfo = movieMapper.selectById(rating.getMovieId());
        assertEquals(479,movieInfo.getRateNums());
        assertEquals(3.01879,movieInfo.getAvgRate(),0.00001);

        //2、 不存在的电影ID
        Rating rating2 = new Rating();
        rating2.setUserId(1);
        rating2.setMovieId(3960);
        rating2.setRating(4);

        // 调用被测试的方法
        BaseResponse<Rating> response2 = rateService.rate(rating);

        // 验证返回结果
        assertEquals(400, response2.getCode());
        assertNull(response.getData());

        // 验证数据库未更新
        MovieInfo movieInfo2 = movieMapper.selectById(rating.getMovieId());
        assertNull(movieInfo2);


        //3、 无效的评分值
        Rating rating3 = new Rating();
        rating3.setUserId(1);
        rating3.setMovieId(124);
        rating3.setRating(6);

        // 调用被测试的方法
        BaseResponse<Rating> response3 = rateService.rate(rating);

        // 验证返回结果
        assertEquals(400, response3.getCode());
        assertNull(response3.getData());

        // 验证数据库未更新
        MovieInfo movieInfo3 = movieMapper.selectById(rating.getMovieId());
        assertNull(movieInfo3);

    }


    @Test

    void getRatedMovie() {

        Integer userId1 = 1;
        // 调用 getRatedMovie 方法获取评分过的电影列表
        BaseResponse<RatedMovie[]> response = rateService.getRatedMovie(userId1);
        RatedMovie[] ratedMovies = response.getData();
        //已评分列表
        System.out.println(ratedMovies.length);
        //验证列表电影属性值
        assertEquals(1, ratedMovies[0].getId());
        assertEquals("Toy Story (1995) ", ratedMovies[0].getName());
        assertEquals("Animation, Children's, Comedy", ratedMovies[0].getType());
        //省略其他属性

        // 不存在评分记录
        Integer userId2 = 6042;
        // 调用被测试的方法
        BaseResponse<RatedMovie[]> response2 = rateService.getRatedMovie(userId2);
        // 验证返回结果
        assertEquals(200, response2.getCode());
        RatedMovie[] ratedMovies2 = response2.getData();
        assertEquals(0, ratedMovies2.length);
    }

    @Test
    void getMovieRateByUserId() {
        Integer userId1 =1;
        Integer movieId1 = 260;
        Integer userId2 =2;
        Integer movieId2 =515 ;

        //1、检查不存在的电影评分
        Integer userId3 =1;
        Integer movieId3 =2;

        //2、检查不存在的id
        Integer userId4 =-1;
        Integer movieId4 =2;

        //3、检查不存在的电影
        Integer userId5 =1;
        Integer movieId5 =-9;
        // 调用 getMovieRateByUserId 方法获取指定用户对指定电影的评分
        BaseResponse<Integer> result1 = rateService.getMovieRateByUserId(userId1, movieId1);
        BaseResponse<Integer> result2 = rateService.getMovieRateByUserId(userId2, movieId2);
        BaseResponse<Integer> result3 = rateService.getMovieRateByUserId(userId3, movieId3);
        BaseResponse<Integer> result4 = rateService.getMovieRateByUserId(userId4, movieId4);
        BaseResponse<Integer> result5 = rateService.getMovieRateByUserId(userId5, movieId5);

        //断言结果是否符合预期
        assertEquals(4, result1.getData());
        assertEquals(5, result2.getData());
        assertEquals(0, result3.getData());
        assertEquals(0, result4.getData());
        assertEquals(0, result5.getData());

    }
}