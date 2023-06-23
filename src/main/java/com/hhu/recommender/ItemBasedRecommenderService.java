package com.hhu.recommender;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.file.FileItemSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class ItemBasedRecommenderService {
    @Value("${modelpath}")
    private String modelpath;
    private GenericItemBasedRecommender recommender;


    public ItemBasedRecommenderService() throws IOException, TasteException {

        }
    public void initialize() throws IOException, TasteException {
        // 加载数据模型
        DataModel dataModel = new FileDataModel(new File(modelpath));
        // 创建物品相似度计算器
        ItemSimilarity itemSimilarity = new PearsonCorrelationSimilarity(dataModel);
        // 创建基于物品的推荐器
        recommender = new GenericItemBasedRecommender(dataModel, itemSimilarity);
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