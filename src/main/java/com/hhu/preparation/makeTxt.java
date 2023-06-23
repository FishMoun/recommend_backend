package com.hhu.preparation;

import com.hhu.entity.Rating;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class makeTxt {
    public static void main(String[] args) {
        try {
            LineNumberReader lineReader = new LineNumberReader(new FileReader(
                    "D:/Data/dataset/ml-1m/ratings.dat"));
            String line = "";
            List<Rating> ratingList = new ArrayList<Rating>();
            FileWriter fileWriter = new FileWriter("D:/Data/dataset/movie_preferences.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter) ;
            while ((line = lineReader.readLine()) != null) {
                String[] ra = line.split("::");
                Rating rating = new Rating();
                rating.setUserId(Integer.parseInt(ra[0]));
		        rating.setMovieId(Integer.parseInt(ra[1]));
                rating.setRating(Integer.parseInt(ra[2]));
                String data = rating.getUserId().toString() +","+ rating.getMovieId().toString() +","+ rating.getRating().toString();

                    bufferedWriter.write(data);
                    bufferedWriter.newLine(); // 写入新行


            }
            bufferedWriter.flush();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    private static Rating fillRating(String line) {

        String[] ra = line.split("::");
        Rating rating = new Rating();
//		rating.setUser_id(Integer.parseInt(ra[0]));
//		rating.setMovie_id(Integer.parseInt(ra[1]));
        rating.setRating(Integer.parseInt(ra[2]));
        return rating;
    }

}
