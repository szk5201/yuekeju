package org.yuekeju.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yuekeju.common.entity.user.UserEntity;
import org.yuekeju.user.service.IUserService;

import com.baomidou.mybatisplus.plugins.Page;

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
		Page page = new Page(1, 10);
		page = iUserService.findDevelopers(page, "NORMAL");
		return page;
	}
}
