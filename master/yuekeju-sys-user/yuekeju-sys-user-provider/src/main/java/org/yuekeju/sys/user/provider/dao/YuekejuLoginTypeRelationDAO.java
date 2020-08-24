package org.yuekeju.sys.user.provider.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.yuekeju.common.entity.user.YuekejuLoginTypeRelationEntity;

/**
 * 用户可登录的平台
 *
 * @author suzk123 * @since 2020-07-09
 */
@Mapper
public interface YuekejuLoginTypeRelationDAO extends BaseMapper<YuekejuLoginTypeRelationEntity> {
}
