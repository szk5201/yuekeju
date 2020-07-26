package org.yuekeju.sys.user.provider.service;

import org.yuekeju.common.entity.user.YuekejuPermissionEntity;
import org.yuekeju.common.vo.ResultVO;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author suzk123
 * @since 2020-07-20
 */
@SuppressWarnings("all")
public interface YuekejuPermissionService extends IService<YuekejuPermissionEntity> {
	/**
	 * 根据父id查询
	 * @param prantId
	 * @return
	 */
	ResultVO findAllPermission(String prantId);
	
	/**
	 * 根据code删除
	 * @param id
	 * @return
	 */
	ResultVO deletePermission(String id);
	/**
	 * 
	 * @param yuekejuPermissionEntity
	 * @return
	 */
	ResultVO insertPermission(YuekejuPermissionEntity yuekejuPermissionEntity);
	
	
	
}
