package com.hhu.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hhu.entity.MovieInfo;
import com.hhu.mapper.MovieMapper;
import com.hhu.recommender.ItemBasedRecommenderService;
import com.hhu.recommender.MyItemBasedRecommender;
import com.hhu.service.RecommendService;
import com.hhu.utils.BaseResponse;
import com.hhu.utils.ResultUtils;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.ItemBasedRecommender;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;



@Service
public class RecommendServiceImpl implements RecommendService {


    @Autowired
    private MovieMapper movieMapper;
    static final  private String POSTERURL = "/api/poster/";
    @Resource
    private MyItemBasedRecommender myRecommender;

    @Autowired
    private ItemBasedRecommenderService recommenderService;

    @Override
    public BaseResponse<MovieInfo[]> recommendByObject(int userId) throws TasteException, IOException {

        List<RecommendedItem> recommendations = recommenderService.recommendItems(userId,5);
        if(recommendations == null)
            return new BaseResponse<>(200, getRandomMovie(5), "该用户没有评价过电影，随机推荐！");
        MovieInfo[] movieInfos = new MovieInfo[5];
        for(int i = 0;i<recommendations.size();++i){
            QueryWrapper<MovieInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", recommendations.get(i).getItemID());
            MovieInfo movieInfo = movieMapper.selectOne(queryWrapper);
            movieInfo.setPosterUrl(POSTERURL+movieInfo.getId()+".jpg");
            movieInfos[i] = movieInfo;
        }
        return ResultUtils.success(movieInfos);


    }

    @Override
    public BaseResponse<MovieInfo[]> recommendByHighScore() {
        QueryWrapper<MovieInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("avg_rate", 4);
        MovieInfo[] waitingmovieInfos = movieMapper.selectList(queryWrapper).toArray(new MovieInfo[0]);
        int num = waitingmovieInfos.length;
        MovieInfo[] movieInfos = new MovieInfo[5];
        l1: for(int i =0;i<5;++i){
            movieInfos[i] = waitingmovieInfos[(int) (Math.random() * num)];
            int j = 0;
            while(j<i)
            {
                if(movieInfos[j].getId() == movieInfos[i].getId())
                    continue l1;
                j++;
            }
            movieInfos[i].setPosterUrl(POSTERURL+movieInfos[i].getId()+".jpg");

        }
        return ResultUtils.success(movieInfos);
    }

    MovieInfo[] getRandomMovie(int num){
        MovieInfo[] movieInfos = new MovieInfo[num];
        int i = 0;
        l1: while(i<num){
            int random =   (int) (Math.random() * 3953);
            //检查编号是否存在
            QueryWrapper<MovieInfo> queryWrapper = new QueryWrapper<>();

            queryWrapper.eq("id", random);
            MovieInfo movieInfo = movieMapper.selectOne(queryWrapper);
            if(movieInfo !=null) {
                movieInfos[i] = movieInfo;
                movieInfo.setPosterUrl(POSTERURL+movieInfo.getId()+".jpg");
                int j = 0;
                while(j<i)
                {
                    if(movieInfos[j].getId() == movieInfo.getId())
                        continue l1;
                    j++;
                }
                ++i;
            }

        }
        return movieInfos;

    }
}
