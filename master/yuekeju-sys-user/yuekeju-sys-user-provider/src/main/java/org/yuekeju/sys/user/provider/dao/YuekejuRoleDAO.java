package org.yuekeju.sys.user.provider.dao;

import java.util.List;
import java.util.Map;

import org.yuekeju.common.entity.user.YuekejuRoleEntity;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author suzk
 * @since 2020-07-09
 */
public interface YuekejuRoleDAO extends BaseMapper<YuekejuRoleEntity> {
    /**
     * 查询角色  全部查询 或者条件查询
     * @param map
     * @return
     */
	List<YuekejuRoleEntity>  findAllBySearch(Map<String,Object> map);
	
	
	
}
