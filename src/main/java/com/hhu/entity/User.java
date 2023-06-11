package com.hhu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "用户的实体类")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class User {
    @ApiModelProperty(value = "用户Id")
    @TableId(type = IdType.AUTO) // 指定主键生成策略
    private Integer userId;
    @ApiModelProperty(value = "用户名",example = "fishmoun",required = true)
    private String userName;
    @ApiModelProperty(value = "密码",example = "0123456",required = true)
    private String password;

    @ApiModelProperty(value = "性别",example = "男")
    private String gender;
    @ApiModelProperty(value = "年龄",example = "18")
    private int age;
    @ApiModelProperty(value = "职业",example = "教育")
    private String occupation;
    @ApiModelProperty(value = "喜好",example = "摄影")
    private String likes;

}
