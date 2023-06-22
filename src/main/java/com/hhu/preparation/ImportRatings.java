package com.hhu.preparation;

import com.hhu.entity.Rating;
import com.hhu.utils.DBUtil;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImportRatings {

	public final static String TABLE_NAME = "ratings";
	public final static String USER_ID_COLUMN = "user_id";
	public final static String MOVIE_ID_COLUMN = "movie_id";
	public final static String RATING = "rating";
	public final static String TIMESTAMP = "timestamp";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			LineNumberReader lineReader = new LineNumberReader(new FileReader(
					"D:/Data/dataset/ml-1m/ratings.dat"));
			String line = "";
			List<Rating> ratingList = new ArrayList<Rating>();
			while ((line = lineReader.readLine()) != null) {
				ratingList.add(fillRating(line));
			}
			insertRatings(ratingList);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
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


	public static void insertRatings(List<Rating> ratings) {
		Connection conn = DBUtil.getJDBCConnection();
		PreparedStatement ps = null;
		String sql = "insert into " + TABLE_NAME + " ( " + USER_ID_COLUMN
				+ ", " + MOVIE_ID_COLUMN + ", " + RATING
				+ ") values (?, ?, ?)";
		try {
			conn.setAutoCommit(false);

			ps = conn.prepareStatement(sql);
			int count = 0;
			for (Rating rating : ratings) {
//				ps.setInt(1, rating.getUser_id());
//				ps.setInt(2, rating.getMovie_id());
				ps.setInt(3, rating.getRating());
				ps.addBatch();
				++count;
				if(count == 1000)
				{
					ps.executeBatch();
					conn.commit();
					count = 0;
				}
			}
			if (count > 0) {
				ps.executeBatch();
				conn.commit();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}