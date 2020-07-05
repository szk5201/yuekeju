package org.yuekeju.user.service;

import org.yuekeju.common.entity.user.UserEntity;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

public interface IUserService extends IService<UserEntity>{
	Page<UserEntity> findDevelopers(Page<UserEntity> page, String status);
}
