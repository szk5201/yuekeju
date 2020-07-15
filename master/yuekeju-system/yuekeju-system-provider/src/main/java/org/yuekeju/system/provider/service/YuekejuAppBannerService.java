package org.yuekeju.system.provider.service;

import java.util.Map;

import org.yuekeju.common.entity.system.YuekejuAppBannerEntity;
import org.yuekeju.common.vo.ResultVO;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * app轮播图 服务类
 * </p>
 *
 * @author suzk123
 * @since 2020-07-09
 */
public interface YuekejuAppBannerService extends IService<YuekejuAppBannerEntity> {
	/**
	 * 新增banner图
	 * 
	 * @param yuekeuAppBannerEntity
	 * @return
	 */
	ResultVO insertBanner(YuekejuAppBannerEntity yuekeuAppBannerEntity);

	/**
	 * 修改banner图
	 * 
	 * @param yuekejuAppBannerEntity
	 * @return
	 */
	ResultVO updateBanner(YuekejuAppBannerEntity yuekejuAppBannerEntity);
	/**
	 * 修改banner图
	 * @param id
	 * @return
	 */
	ResultVO deleteBanner(Integer[] id);
	/**
	 * 查询banner图
	 */
	ResultVO findBanner(Map<String,Object> map);
}
