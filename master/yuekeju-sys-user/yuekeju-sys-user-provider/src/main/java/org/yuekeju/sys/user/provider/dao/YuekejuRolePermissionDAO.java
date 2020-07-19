package org.yuekeju.sys.user.provider.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.yuekeju.common.entity.user.YuekejuRolePermissionEntity;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 角色权限关系表 Mapper 接口
 * </p>
 *
 * @author suzk123
 * @since 2020-07-09
 */
@Mapper
public interface YuekejuRolePermissionDAO extends BaseMapper<YuekejuRolePermissionEntity> {
	/**
	 * 查询权限，根据条件查询
	 * @param param
	 * @return
	 */
	List<YuekejuRolePermissionEntity>    findAllPermisssionBySearch(Map<String, Object> param);
}
