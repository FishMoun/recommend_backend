package com.hhu.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.hhu.entity.MovieInfo;
import com.hhu.entity.Rating;
import com.hhu.entity.User;
import com.hhu.mapper.MovieMapper;
import com.hhu.mapper.RatingMapper;
import com.hhu.service.RateService;
import com.hhu.utils.BaseResponse;
import com.hhu.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RateServiceImpl implements RateService {
    @Autowired
    private RatingMapper ratingMapper;
    @Autowired
    private MovieMapper movieMapper;
    @Value("${modelpath}")
    private  String modelpath;
    @Override
    public BaseResponse<Rating> rate(Rating rating) {


        //更新电影的平均分和评价人数
        UpdateWrapper<MovieInfo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.setSql("avg_rate = (avg_rate * rate_nums + "+rating.getRating()+" )/(rate_nums + 1)")
                .setSql("rate_nums = rate_nums +1")
                .eq("id",rating.getMovieId());
        int affectRows = movieMapper.update(null,updateWrapper);
        if(affectRows == 0)
            return ResultUtils.error(400,"评分失败");

        //更新模型文件
        String data = rating.getUserId().toString() +","+ rating.getMovieId().toString() +","+ rating.getRating().toString();
        try (FileWriter fileWriter = new FileWriter(modelpath, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(data);
            bufferedWriter.newLine(); // 写入新行
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //插入用户评分

        System.out.println(ratingMapper.insert(rating));
        return ResultUtils.success(rating);
    }

    @Override
    public BaseResponse<MovieInfo[]> getRatedMovie(Integer userId) {
        QueryWrapper<Rating> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        Rating[] ratings = ratingMapper.selectList(queryWrapper).toArray(new Rating[0]);
        List<Integer> movieIds = new ArrayList<>();
        for(int i =0;i<ratings.length;++i){
            movieIds.add(ratings[i].getMovieId())  ;
        }
        MovieInfo[] movieInfos = movieMapper.selectBatchIds(movieIds).toArray(new MovieInfo[0]);
        return ResultUtils.success(movieInfos);
    }
}
