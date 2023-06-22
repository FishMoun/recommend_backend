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
    @ApiModelProperty(value = "职业",example = "其他，教育，艺术家，行政，大学生，客户服务，医疗，管理，农民，家庭主妇，中小学生，律师，程序员，退休，市场营销，科学家，个体户，工程师，工匠，失业，作家")
    private String occupation;
    @ApiModelProperty(value = "喜好(多个爱好用逗号隔开，字符串形式)",example = "喜剧,科幻")
    private String likes;

}
