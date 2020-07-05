package org.yuekeju.user.service.impl;

import org.springframework.stereotype.Service;
import org.yuekeju.common.entity.user.UserEntity;
import org.yuekeju.user.dao.UserDAO;
import org.yuekeju.user.service.IUserService;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
@Service
public class IUserServiceImpl  extends ServiceImpl<UserDAO, UserEntity> implements IUserService {

	@Override
	public Page<UserEntity> findDevelopers(Page<UserEntity> page, String status) {
		// TODO Auto-generated method stub
		page.setRecords(baseMapper.findUserByLoginName(page, status));
		return page;
	}
	
	
	
}
