package org.yuekeju.user.service;

import org.yuekeju.common.entity.user.UserEntity;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
/**
 * 数据访问层
 * @author szk  2020-7-3
 */
public interface IUserService extends IService<UserEntity>{
	/**
	 * 根据条件全查所有用户
	 * @param page
	 * @param status
	 * @return
	 */
	Page<UserEntity> findDevelopers(Page<UserEntity> page, String status);
}
