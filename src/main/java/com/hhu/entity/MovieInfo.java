/**
 * movie的javabean
 */
package com.hhu.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "电影的实体类")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("movie")
public class MovieInfo {
	@ApiModelProperty(value = "电影Id",example = "123",required = true)
	private Integer id;
	@ApiModelProperty(value = "电影名称",example = "abc",required = true)
	private String name;
	@ApiModelProperty(value = "发行年份",example = "abc",required = true)
	private String publishedYear;
	@ApiModelProperty(value = "类别",example = "abc",required = true)
	private String type;
	@ApiModelProperty(value = "海报地址",example = "abc",required = true)
	private String posterUrl;
	@ApiModelProperty(value = "电影介绍",example = "abc",required = true)
	private String introduction;
	@ApiModelProperty(value = "评分",example = "abc",required = true)
	private double avgRate;
	@ApiModelProperty(value = "评价人数",example = "abc",required = true)
	private int rateNums;
}
