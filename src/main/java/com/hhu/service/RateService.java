package com.hhu.service;

import com.hhu.entity.MovieInfo;
import com.hhu.entity.Rating;
import com.hhu.utils.BaseResponse;

public interface RateService {
    BaseResponse<Rating> rate(Rating rating);

    BaseResponse<MovieInfo[]> getRatedMovie(Integer userId);
}
