package org.yuekeju.sys.user.provider.dao;

import org.apache.ibatis.annotations.Mapper;
import org.yuekeju.common.entity.user.YuekejuSysRoleUserEntity;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 管理用户角色关系表 Mapper 接口
 * </p>
 *
 * @author suzk123
 * @since 2020-07-09
 */
@Mapper
public interface YuekejuSysRoleUserDAO extends BaseMapper<YuekejuSysRoleUserEntity> {

}
