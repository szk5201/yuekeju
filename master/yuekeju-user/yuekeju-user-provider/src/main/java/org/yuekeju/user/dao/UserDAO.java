
package org.yuekeju.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.yuekeju.common.entity.user.UserEntity;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
/**
 * 数据访问层
 * @author szk  2020-7-3
 */
@Mapper
public interface UserDAO extends BaseMapper<UserEntity>{
	/**
	 * 根据条件全查所有用户
	 * @param page
	 * @param status
	 * @return UserEntity
	 */
	List<UserEntity> findUserByLoginName(Pagination page,String status);
	
}
