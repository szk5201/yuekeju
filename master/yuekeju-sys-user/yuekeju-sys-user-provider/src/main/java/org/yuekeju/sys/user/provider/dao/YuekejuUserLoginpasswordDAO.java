package org.yuekeju.sys.user.provider.dao;

import org.apache.ibatis.annotations.Mapper;
import org.yuekeju.common.entity.user.YuekejuUserLoginpasswordEntity;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 用户登录密码历史表 Mapper 接口
 * </p>
 *
 * @author suzk123
 * @since 2020-07-09
 */
@Mapper
public interface YuekejuUserLoginpasswordDAO extends BaseMapper<YuekejuUserLoginpasswordEntity> {

}
