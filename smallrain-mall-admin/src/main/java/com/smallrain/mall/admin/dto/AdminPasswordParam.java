package com.smallrain.mall.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AdminPasswordParam {

  @ApiModelProperty(value = "用户名", required = true)
  private String username;
  @ApiModelProperty(value = "旧密码", required = true)
  private String oldPassword;
  @ApiModelProperty(value = "新密码", required = true)
  private String newPassword;
}