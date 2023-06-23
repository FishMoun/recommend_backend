package com.hhu.controller;

import com.hhu.entity.LoginUser;
import com.hhu.entity.MovieInfo;
import com.hhu.service.RecommendService;
import com.hhu.utils.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "推荐模块",description = "推荐高分电影、推荐相似口味电影")
@RestController
public class RecommendController {
    @Resource
    RecommendService recommendService;
    /*
    * 1推荐算法推荐
    * */
    @ApiOperation(value = "基于相似度实现推荐的接口")
    @ApiResponses({@ApiResponse(responseCode = "200",description = "ok"),@ApiResponse(responseCode = "201",description = "该用户没有评价过电影，随机推荐！"),@ApiResponse(responseCode = "400",description = "出错！")})
    @PostMapping("/recommend/{userId}")
    public BaseResponse<MovieInfo []> recommend(@ApiParam(value= "用户Id",required = true)  @PathVariable int userId){


        return recommendService.recommendByObject(userId);
    }


    @ApiOperation(value = "高分电影推荐的接口")
    @ApiResponses({@ApiResponse(responseCode = "200",description = "ok"),@ApiResponse(responseCode = "400",description = "出错！")})
    @GetMapping("/highscore")
    public BaseResponse<MovieInfo []> highscorerecommend(){

        return recommendService.recommendByHighScore();
    }
}
