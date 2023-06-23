package com.hhu.recommender;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
public class MyItemBasedRecommender {
	@Value("${modelpath}")
	private  String modelpath;

	//物品
	public  List<RecommendedItem> myItemBasedRecommender(long userID, int size){
		List<RecommendedItem> recommendations = null;
		try {

				DataModel model = new FileDataModel(new File(modelpath));//构造数据模型
				ItemSimilarity similarity = new PearsonCorrelationSimilarity(model);//计算内容相似度
				Recommender recommender  = new GenericItemBasedRecommender(model, similarity);//构造推荐引擎

			recommendations = recommender.recommend(userID, size);//得到推荐结果

		} catch (Exception e) {

		}
		return recommendations;
	}

}
