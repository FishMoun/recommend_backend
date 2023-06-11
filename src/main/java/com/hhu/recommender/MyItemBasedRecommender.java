package com.hhu.recommender;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MyItemBasedRecommender {
	//物品
	public static List<RecommendedItem> myItemBasedRecommender(long userID, int size){
		List<RecommendedItem> recommendations = null;
		try {
			DataModel model = new FileDataModel(new File("D:/Data/dataset/ml-1m/movie_preferences.txt"));//构造数据模型
			ItemSimilarity similarity = new PearsonCorrelationSimilarity(model);//计算内容相似度  
			Recommender recommender = new GenericItemBasedRecommender(model, similarity);//构造推荐引擎  
			recommendations = recommender.recommend(userID, size);//得到推荐结果
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return recommendations;
	}
	public static void main(String[] args){
		List<RecommendedItem> recommendations = myItemBasedRecommender(2,5);
		for(int i = 0;i<recommendations.size();++i){
			System.out.println(recommendations.get(i).getItemID());
		}

	}
}
