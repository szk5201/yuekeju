package org.yuekeju.sys.user.provider.dao;

import java.util.List;
import java.util.Map;

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
	/**
	 * 查询资源表的目录和菜单
	 * @param prantId  父级id
	 * @return
	 */
	List<YuekejuPermissionEntity> findAllByMenuPermission(String prantId);
	/**
	 * 根据code 查询
	 * @param yuekejuCode 唯一标识
	 * @return
	 */
	List<YuekejuPermissionEntity> findByYuekejuCode(String yuekejuCode);
	/**
	 * 修改删除状态 变为1 已删除
	 * @param yuekejuCode
	 * @return
	 */
	int updatePermissionDelTag(Map<String, Object> map);
}
