package org.yuekeju.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yuekeju.common.entity.user.UserEntity;
import org.yuekeju.user.service.IUserService;

import com.baomidou.mybatisplus.plugins.Page;

/**
 * 用户数据交互层
 * @author szk  2020-7-3
 */
@RestController
public class UserController {
	@Autowired
	IUserService iUserService;

	@RequestMapping("/show")
	public String show(@RequestBody String token) {
		return token;
	}

	@RequestMapping("/list")
	public Page<UserEntity> list() {
		Page<UserEntity> page = new Page<UserEntity>(1, 10);
		page = iUserService.findDevelopers(page, "NORMAL");
		return page;
	}
}
