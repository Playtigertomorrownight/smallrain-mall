package com.smallrain.mall.admin.service;

import com.smallrain.mall.admin.dto.AdminLoginParam;

/**
 * 后台管理员Service
 * Created by macro on 2018/4/26.
 */
public interface AdminUserService {
    
	/**
     * 管理员登录功能
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    String login(AdminLoginParam loginParam);
	
}
