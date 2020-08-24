package org.yuekeju.sys.user.provider.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.yuekeju.common.entity.user.UserEntity;
import org.yuekeju.common.vo.user.YuekejuUserVo;

import java.util.List;
/**
 * 2020-07-10
 * @author szk
 * 用户数据访问层
 */
@Mapper
public interface UserDAO extends BaseMapper<UserEntity>{
	/**
	 * 根据账号密码查询是否存在此用户
	 *
	 * @param userEntity
	 * @return
	 */
	List<UserEntity> findUserByLoginName(UserEntity userEntity);

	/**
	 * 根据条件查询 全查 并分页
	 * @param page
	 * @param yuekejuUserVo
	 * @return
	 */
	List<UserEntity> findUserAllBySearch(Pagination page, YuekejuUserVo yuekejuUserVo);

	/**
	 * 根据code 查询用户盐
	 *
	 * @param yuekejuCode
	 * @return
	 */
	String findSaltByCode(@Param("yuekejuCode") String yuekejuCode, @Param("loginName") String loginName);

	/**
	 * 修改用户信息
	 *
	 * @param userEntity
	 * @return
	 */
	int updateUser(UserEntity userEntity);
}
