/**
 * movie的javabean
 */
package com.hhu.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "电影的实体类")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("movie")
public class MovieInfo {
	private Integer id;
	private String name;
	private String publishedYear;
	private String type;
	private String posterUrl;
	private String introduction;

	private double avgRate;
	private int rateNums;


}
