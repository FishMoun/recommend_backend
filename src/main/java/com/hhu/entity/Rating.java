package com.hhu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "评分的实体类")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("ratings")
public class Rating {
	@ApiModelProperty(value = "用户Id",example = "123",required = true)
	private Integer userId;
	@ApiModelProperty(value = "电影Id",example = "456",required = true)
	private Integer movieId;
	@ApiModelProperty(value = "电影评分(1~5的整数)",example = "1",required = true)
	private Integer rating;


}