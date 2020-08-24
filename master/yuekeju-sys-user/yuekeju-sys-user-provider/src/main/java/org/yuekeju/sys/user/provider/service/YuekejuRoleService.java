package org.yuekeju.sys.user.provider.service;

import com.baomidou.mybatisplus.service.IService;
import org.yuekeju.common.entity.user.YuekejuRoleEntity;
import org.yuekeju.common.vo.ResultVO;

import java.util.Map;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author suzk
 * @since 2020-07-09
 */
@SuppressWarnings("all")
public interface YuekejuRoleService extends IService<YuekejuRoleEntity> {
	  /**
     * 查询角色  全部查询 或者条件查询
     * @param map
	   * @param tokenId
	   * @return
     */
	  ResultVO findAllBySearch(Map<String, Object> map, String tokenId);
	/**
	 * 根据id 查询角色
	 * @param paramMap
	 * @param tokenId
	 * @return
	 */
	ResultVO findRoleByCode(Map<String, Object> paramMap, String tokenId);
	/**
	 * 新增角色
	 * @param yuekejuRoleEntity
	 * @param tokenId
	 * @return
	 */
	ResultVO insertRole(YuekejuRoleEntity yuekejuRoleEntity, String tokenId);
	/**
	 * 根据中文名称和英文名称查询
	 * @param yuekejuRoleEntity
	 * @param tokenId
	 * @return
	 */
	ResultVO findRoleNameByCnAndEn(YuekejuRoleEntity yuekejuRoleEntity, String tokenId);

	/**
	 * 删除角色
	 *
	 * @param ids
	 * @param tokenId
	 * @return
	 */
	ResultVO deleteRole(String[] ids, String tokenId);
}
