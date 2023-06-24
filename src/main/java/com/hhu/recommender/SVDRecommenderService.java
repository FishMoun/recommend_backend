package com.hhu.recommender;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.svd.SVDPlusPlusFactorizer;
import org.apache.mahout.cf.taste.impl.recommender.svd.SVDRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class SVDRecommenderService {
    @Value("${modelpath}")
    private String modelpath;
    private SVDRecommender recommender;

    public void initialize() throws IOException, TasteException {
        // 加载数据模型
        DataModel model = new FileDataModel(new File(modelpath));
        
        // 创建SVD推荐器的Factorizer
        int numFeatures = 10;
        int numIterations = 10;
        SVDPlusPlusFactorizer factorizer = new SVDPlusPlusFactorizer(model, numFeatures, numIterations);
        // 创建SVD推荐器
        recommender = new SVDRecommender(model, factorizer);
    }

    public List<RecommendedItem> recommendItems(long userId, int numItems) throws TasteException, IOException {
        if(recommender == null)
            initialize();
        // 调用推荐器的推荐方法
        List<RecommendedItem> recommendations = null;
        try {
            recommendations = recommender.recommend(userId, numItems);
        }catch (Exception e){

        }
        return recommendations;
    }
}
