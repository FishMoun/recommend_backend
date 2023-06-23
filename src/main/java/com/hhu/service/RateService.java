package com.hhu.service;

import com.hhu.entity.MovieInfo;
import com.hhu.entity.RatedMovie;
import com.hhu.entity.Rating;
import com.hhu.utils.BaseResponse;

public interface RateService {
    BaseResponse<Rating> rate(Rating rating);

    BaseResponse<RatedMovie[]> getRatedMovie(Integer userId);

    BaseResponse<Integer> getMovieRateByUserId(Integer userId, Integer movieId);
}
