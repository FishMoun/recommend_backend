package com.hhu.controller;


import com.hhu.entity.LoginUser;
import com.hhu.entity.MovieInfo;
import com.hhu.entity.RatedMovie;
import com.hhu.entity.Rating;
import com.hhu.service.RateService;
import com.hhu.service.UserService;
import com.hhu.utils.BaseResponse;
import com.hhu.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "评分模块",description = "用户评分功能")
@RestController
public class RateController {

    @Resource
    RateService rateService;
    @ApiOperation(value = "评分接口")
    @ApiResponses({@ApiResponse(responseCode = "200",description = "评分成功！"),@ApiResponse(responseCode = "400",description = "评价失败请稍后重试")})
    @PostMapping("/rate")
    public BaseResponse<Rating> rate(@ApiParam(value= "评分实体",required = true)  @RequestBody Rating rating){

        return rateService.rate(rating);
    }

    @ApiOperation(value = "获取用户已评价的电影的接口")
    @ApiResponses({@ApiResponse(responseCode = "200",description = "获取成功！"),@ApiResponse(responseCode = "400",description = "获取失败请稍后重试")})
    @PostMapping("/ratedmovie/{userId}")
    public BaseResponse<RatedMovie[]> getRatedMovie(@PathVariable Integer userId){

        return rateService.getRatedMovie(userId);
    }

    @ApiOperation(value = "获取用户对一个电影的评分接口,未评分会返回的评分是0")
    @ApiResponses({@ApiResponse(responseCode = "200",description = "获取成功！"),@ApiResponse(responseCode = "400",description = "获取失败请稍后重试")})
    @PostMapping("/getrate/{userId}/{movieId}")
    public BaseResponse<Integer> getMovieRateByUserId(@PathVariable Integer userId,@PathVariable Integer movieId){

        return rateService.getMovieRateByUserId(userId,movieId);
    }
}
