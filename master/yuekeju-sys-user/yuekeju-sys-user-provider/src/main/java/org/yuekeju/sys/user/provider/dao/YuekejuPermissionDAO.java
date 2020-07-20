package org.yuekeju.sys.user.provider.dao;

import org.apache.ibatis.annotations.Mapper;
import org.yuekeju.common.entity.user.YuekejuPermission;

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
public interface YuekejuPermissionDAO extends BaseMapper<YuekejuPermission> {

}
