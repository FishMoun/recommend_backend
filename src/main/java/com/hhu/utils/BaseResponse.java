package com.hhu.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ExampleProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 通用返回类
 *
 * @param <T>
 * @author yupi
 */
@ApiModel(description = "通用返回对象的实体类")
@Data
public class BaseResponse<T> implements Serializable {

    @ApiModelProperty(value = "状态码",example = "200")
    private int code;

    private T data;
    @ApiModelProperty(value = "返回信息",example = "成功返回（仅作示例）")
    private String message;

    public BaseResponse(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public BaseResponse(int code, T data) {
        this(code, data, "");
    }

    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(), null, errorCode.getMessage());
    }
}
