package org.yuekeju.sys.user.provider.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.yuekeju.common.entity.user.YuekejuPermissionEntity;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author suzk123
 * @since 2020-07-20
 */
@Mapper
public interface YuekejuPermissionDAO extends BaseMapper<YuekejuPermissionEntity> {
	/**
	 * 查询资源表
	 * @param prantId  父级id
	 * @return
	 */
	List<YuekejuPermissionEntity> findAllPermission(String prantId);
	
	
	
	
}
