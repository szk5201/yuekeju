package org.yuekeju.user.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.yuekeju.common.entity.user.UserEntity;
import org.yuekeju.user.dao.UserDAO;
import org.yuekeju.user.service.IUserService;
/**
 * 数据访问层服务
 * @author szk  2020-7-3
 */
@Service
public class IUserServiceImpl  extends ServiceImpl<UserDAO, UserEntity> implements IUserService {

	@Override
	public Page<UserEntity> findDevelopers(Page<UserEntity> page, String status) {
		//  Auto-generated method stub
		page.setRecords(baseMapper.findUserByLoginName(page, status));
		return page;
	}
	
}
