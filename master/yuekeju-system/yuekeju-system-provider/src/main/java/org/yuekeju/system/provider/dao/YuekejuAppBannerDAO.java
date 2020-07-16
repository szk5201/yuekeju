package org.yuekeju.system.provider.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.yuekeju.common.entity.system.YuekejuAppBannerEntity;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * app轮播图 Mapper 接口
 * </p>
 *
 * @author suzk123
 * @since 2020-07-09
 */
@Mapper
public interface YuekejuAppBannerDAO extends BaseMapper<YuekejuAppBannerEntity> {
	/**
	 * 全查 条件查询
	 * @param yuekejuAppBannerEntity   主键查询，删除标记查询，状态查询
	 * @return
	 */
	List<YuekejuAppBannerEntity> findBannerBySeach(Map<String,Object> paramMap);
}
