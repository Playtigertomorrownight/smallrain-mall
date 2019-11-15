package com.smallrain.mall.admin.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.macro.mall.common.api.CommonResult;
import com.macro.mall.model.UmsAdmin;
import com.smallrain.mall.admin.dto.AdminAddParam;
import com.smallrain.mall.admin.dto.AdminLoginParam;
import com.smallrain.mall.common.beans.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 后台用户管理
 * Created by macro on 2018/4/26.
 */
@RestController
@Api(tags = "后台用户管理")
@RequestMapping("/admin")
public class AdminUserController {

  @ApiOperation(value = "admin 登录以后返回token")
  @PostMapping(value = "/login")
  public Result login(@RequestBody AdminLoginParam loginParam) {
      String token = adminService.login(umsAdminLoginParam.getUsername(), umsAdminLoginParam.getPassword());
      if (token == null) {
          return CommonResult.validateFailed("用户名或密码错误");
      }
      Map<String, String> tokenMap = new HashMap<>();
      tokenMap.put("token", token);
      tokenMap.put("tokenHead", tokenHead);
      return CommonResult.success(tokenMap);
  }
  
  @ApiOperation(value = "管理员添加")
  @RequestMapping(value = "/register", method = RequestMethod.POST)
  @ResponseBody
  public Result register(@RequestBody AdminAddParam adminParam) {
      UmsAdmin umsAdmin = adminService.register(umsAdminParam);
      if (umsAdmin == null) {
        Result.failed();
      }
      return Result.success(umsAdmin);
  }
  
}
