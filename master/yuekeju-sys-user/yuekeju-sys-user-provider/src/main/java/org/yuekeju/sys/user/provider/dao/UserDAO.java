package org.yuekeju.sys.user.provider.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.yuekeju.common.entity.user.UserEntity;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

@Mapper
public interface UserDAO extends BaseMapper<UserEntity>{
	/**
	 * 根据条件查询 全查 并分页
	 * @param page
	 * @param userEntity
	 * @return
	 */
	List<UserEntity> findUserByLoginName(Pagination page,UserEntity userEntity);
}
