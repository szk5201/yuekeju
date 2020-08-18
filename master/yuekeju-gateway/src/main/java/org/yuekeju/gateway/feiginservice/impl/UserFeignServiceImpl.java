package org.yuekeju.gateway.feiginservice.impl;

import org.yuekeju.common.entity.user.UserEntity;
import org.yuekeju.gateway.feiginservice.UserFeignService;

import com.baomidou.mybatisplus.plugins.Page;
/**
 * 2020-7-10
 * @author szk
 * 用户服务远程调用实现
 */
public class UserFeignServiceImpl implements UserFeignService{

	@Override
	public Page<UserEntity> findUser() {
		//  Auto-generated method stub
		return null;
	}

}
