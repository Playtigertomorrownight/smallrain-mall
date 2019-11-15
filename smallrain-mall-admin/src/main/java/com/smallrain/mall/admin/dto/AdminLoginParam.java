package com.smallrain.mall.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户登录参数
 * Created by macro on 2018/4/26.
 */
@Data
public class AdminLoginParam {
  
    @ApiModelProperty(value = "用户名", required = true)
    private String username;
    @ApiModelProperty(value = "密码", required = true)
    private String password;
    @ApiModelProperty(value = "记住我", required = false)
    private boolean rememberMe;
    
}
