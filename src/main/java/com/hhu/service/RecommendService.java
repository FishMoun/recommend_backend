package com.hhu.service;

import com.hhu.entity.MovieInfo;
import com.hhu.utils.BaseResponse;
import org.apache.mahout.cf.taste.common.TasteException;

import java.io.IOException;

public interface RecommendService {

    BaseResponse<MovieInfo[]> recommendByObject(int userId) throws TasteException, IOException;

    BaseResponse<MovieInfo[]> recommendByHighScore();
}
