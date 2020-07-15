package org.yuekeju.system.provider.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yuekeju.common.constants.CommonConstants;
import org.yuekeju.common.entity.system.YuekejuAppBannerEntity;
import org.yuekeju.common.util.RedisUtil;
import org.yuekeju.common.vo.ResultEnum;
import org.yuekeju.common.vo.ResultVO;
import org.yuekeju.system.provider.dao.YuekejuAppBannerDAO;
import org.yuekeju.system.provider.service.YuekejuAppBannerService;

/**
 * <p>
 * app轮播图 服务实现类
 * </p>
 *
 * @author suzk123
 * @since 2020-07-09
 */
@Service
@SuppressWarnings("all")
@Transactional
public class YuekejuAppBannerServiceImpl extends ServiceImpl<YuekejuAppBannerDAO, YuekejuAppBannerEntity> implements YuekejuAppBannerService {
	@Autowired
	private RedisUtil redisUtil;
	
	
	/**
	 * 新增banner图
	 */
	@Override
	@SuppressWarnings("all")
	public ResultVO insertBanner(YuekejuAppBannerEntity yuekeuAppBannerEntity) {
		ResultVO resultVO = new ResultVO();
		Integer insert = baseMapper.insert(yuekeuAppBannerEntity);
		
		
		if(insert==0){
			return  new ResultVO(ResultEnum.SYSTEMERROR.getCode(),CommonConstants.FALSE,ResultEnum.SYSTEMERROR.getMessage(), null);
		}
		
		
		
		return  new ResultVO(ResultEnum.INSERTSUCCESS.getCode(),CommonConstants.TRUE,ResultEnum.INSERTSUCCESS.getMessage(), null);
	}
	/**
	 *  修改banner图
	 */
	@Override
	@SuppressWarnings("all")
	public ResultVO updateBanner(YuekejuAppBannerEntity yuekejuAppBannerEntity) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultVO deleteBanner(Integer[] id) {
		Integer deleteById = baseMapper.deleteById(id);
		//baseMapper.deleteBatchIds(id);
		return null;
	}
	@Override
	public ResultVO findBanner(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}
