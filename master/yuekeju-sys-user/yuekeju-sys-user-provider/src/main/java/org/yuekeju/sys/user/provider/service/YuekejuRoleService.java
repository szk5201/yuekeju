package org.yuekeju.sys.user.provider.service;

import java.util.List;
import java.util.Map;

import org.yuekeju.common.entity.user.YuekejuRoleEntity;
import org.yuekeju.common.vo.ResultVO;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author suzk
 * @since 2020-07-09
 */
public interface YuekejuRoleService extends IService<YuekejuRoleEntity> {
	  /**
     * 查询角色  全部查询 或者条件查询
     * @param map
     * @return
     */
	ResultVO  findAllBySearch(Map<String,Object> map);
	/**
	 * 根据id 查询角色
	 */
	ResultVO findRoleByCode(Map<String,Object> paramMap);
	
}
