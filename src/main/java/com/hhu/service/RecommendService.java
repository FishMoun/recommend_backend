package com.hhu.service;

import com.hhu.entity.MovieInfo;
import com.hhu.utils.BaseResponse;

public interface RecommendService {

    BaseResponse<MovieInfo[]> recommendByObject(int userId);

    BaseResponse<MovieInfo[]> recommendByHighScore();
}
