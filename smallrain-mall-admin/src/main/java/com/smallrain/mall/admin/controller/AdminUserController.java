package com.smallrain.mall.admin.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smallrain.mall.admin.dto.AdminLoginParam;
import com.smallrain.mall.admin.service.AdminUserService;
import com.smallrain.mall.common.beans.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 后台用户管理 Created by macro on 2018/4/26.
 */
@RestController
@Api(tags = "后台用户管理")
@RequestMapping("/admin")
public class AdminUserController {

	@Autowired
	private AdminUserService adminUserService;

	@ApiOperation(value = "admin 登录以后返回token")
	@PostMapping(value = "/login")
	public Result login(@RequestBody AdminLoginParam loginParam) {
		String token = adminUserService.login(loginParam);
		if (token == null) {
			return Result.validateFailed("用户名或密码错误");
		}
		Map<String, String> tokenMap = new HashMap<>();
		tokenMap.put("token", token);
		tokenMap.put("tokenHead", tokenHead);
		return Result.success(tokenMap);
	}

}
