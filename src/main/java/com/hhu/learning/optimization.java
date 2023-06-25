package com.hhu.learning;

import com.hhu.recommender.SVDRecommenderService;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.RMSRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.svd.RatingSGDFactorizer;
import org.apache.mahout.cf.taste.impl.recommender.svd.SVDRecommender;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class optimization {
//    @Value("${modelpath}")
    private String modelpath="D:/Data/dataset/ml-1m/movie_preferences.txt";
    private SVDRecommender recommender;

    public void initialize() throws IOException, TasteException {
        // 加载数据模型
        DataModel model = new FileDataModel(new File(modelpath));
        // 创建一个RMSRecommenderEvaluator对象
        RecommenderEvaluator evaluator = new RMSRecommenderEvaluator();
        int featureNum=50;
        int iterations=750;
        double score=1;
        //先设置10个值进行RMSE对比
        for (int i = 0; i < 10; i++) {
            // 创建一个RecommenderBuilder对象，用于构建推荐器
            int finalFeatureNum = featureNum;
            int finalIterations = iterations;
//            System.out.println(finalIterations);
            System.out.println(finalFeatureNum);
            RecommenderBuilder builder = new RecommenderBuilder() {
                @Override
                public SVDRecommender buildRecommender(DataModel dataModel) throws TasteException {
                    // 创建一个RatingSGDFactorizer对象
                    RatingSGDFactorizer factorizer = new RatingSGDFactorizer(dataModel, finalFeatureNum, finalIterations);
                    // 返回一个SVDRecommender对象
                    return new SVDRecommender(dataModel, factorizer);
                }
            };
//        // 创建SVD推荐器的Factorizer
//        int numFeatures = 100;
//        int numIterations = 100;
//        RatingSGDFactorizer factorizer = new RatingSGDFactorizer(model, numFeatures, numIterations);
//        // 创建SVD推荐器
//        recommender = new SVDRecommender(model, factorizer);
            double newscore = evaluator.evaluate(builder,null,model,0.9,1.0);
            if (newscore<score) score=newscore;
            featureNum+=100;
//            iterations+=100;
        }
        System.out.println(score);
    }

//    public List<RecommendedItem> recommendItems(long userId, int numItems) throws TasteException, IOException {
//        if(recommender == null)
//            initialize();
//        // 调用推荐器的推荐方法
//        List<RecommendedItem> recommendations = null;
//        try {
//            recommendations = recommender.recommend(userId, numItems);
//        }catch (Exception e){
//
//        }
//        return recommendations;
//    }
    public static void main(String[] args) throws IOException, TasteException {
        optimization op =new optimization();
        op.initialize();

    }
}
