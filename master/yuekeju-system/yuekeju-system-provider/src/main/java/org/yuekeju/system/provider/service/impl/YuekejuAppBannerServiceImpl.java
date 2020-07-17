package org.yuekeju.system.provider.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yuekeju.common.constants.CommonConstants;
import org.yuekeju.common.entity.system.YuekejuAppBannerEntity;
import org.yuekeju.common.entity.user.UserEntity;
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
@Slf4j
public class YuekejuAppBannerServiceImpl extends ServiceImpl<YuekejuAppBannerDAO, YuekejuAppBannerEntity> implements YuekejuAppBannerService {
	@Autowired
	private RedisUtil redisUtil;
	
	
	/**
	 * 新增banner图
	 */
	@Override
	@SuppressWarnings("all")
	public ResultVO insertBanner(YuekejuAppBannerEntity yuekeuAppBannerEntity) {
		try {
			Integer insert = baseMapper.insert(yuekeuAppBannerEntity);
			if(insert==0){
				return  new ResultVO(ResultEnum.INSERTERROR.getCode(),CommonConstants.FALSE,CommonConstants.BANNER_NAME+ResultEnum.INSERTERROR.getMessage(), null);
			}
			return  new ResultVO(ResultEnum.INSERTSUCCESS.getCode(),CommonConstants.TRUE,CommonConstants.BANNER_NAME+ResultEnum.INSERTSUCCESS.getMessage(), null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  new ResultVO(ResultEnum.SYSTEMERROR.getCode(),CommonConstants.FALSE,ResultEnum.SYSTEMERROR.getMessage(), null);
		
	}
	/**
	 *  修改banner图
	 */
	@Override
	@SuppressWarnings("all")
	public ResultVO updateBanner(YuekejuAppBannerEntity yuekejuAppBannerEntity) {
		try {
			//根据id查询banner图
			Map<String,Object> paramMap= new HashMap<String, Object>(2);
			paramMap.put("id",yuekejuAppBannerEntity.getId());
			List<YuekejuAppBannerEntity> findBannerBySeach = baseMapper.findBannerBySeach(paramMap);
			//判断是否为空
			if(findBannerBySeach==null || findBannerBySeach.isEmpty()){
				return  new ResultVO(ResultEnum.SELECTBYIDERROR.getCode(),CommonConstants.FALSE,CommonConstants.BANNER_NAME+ResultEnum.SELECTBYIDERROR.getMessage(), null);
			}
			//判断时间是否一致
			if(findBannerBySeach.get(0).getUpdateTime().getTime() != yuekejuAppBannerEntity.getUpdateTime().getTime()){
				
				return  new ResultVO(ResultEnum.UPDATETIMEERROR.getCode(),CommonConstants.FALSE,ResultEnum.UPDATETIMEERROR.getMessage(), null);
			}
			//修改
			Integer updateById = baseMapper.updateById(yuekejuAppBannerEntity);
			if(updateById==0){
				return  new ResultVO(ResultEnum.UPDATEERROR.getCode(),CommonConstants.FALSE,CommonConstants.BANNER_NAME+ResultEnum.UPDATEERROR.getMessage(), null);
			}
			
			return  new ResultVO(ResultEnum.UPDATESUCCESS.getCode(),CommonConstants.TRUE,CommonConstants.BANNER_NAME+ResultEnum.UPDATESUCCESS.getMessage(), null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  new ResultVO(ResultEnum.SYSTEMERROR.getCode(),CommonConstants.FALSE,ResultEnum.SYSTEMERROR.getMessage(), null);
	}
	/**
	 * 删除banner图
	 */
	@Override
	public ResultVO deleteBanner(List<Integer> id) {
		try {
			Integer deleteById = baseMapper.deleteBatchIds(id);
			if(deleteById>0){
				return  new ResultVO(ResultEnum.DELETESUCCESS.getCode(),CommonConstants.TRUE,CommonConstants.BANNER_NAME+ResultEnum.DELETESUCCESS.getMessage(), null);
			}
			
			return  new ResultVO(ResultEnum.DELETEERROR.getCode(),CommonConstants.FALSE,CommonConstants.BANNER_NAME+ResultEnum.DELETEERROR.getMessage(), null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  new ResultVO(ResultEnum.SYSTEMERROR.getCode(),CommonConstants.FALSE,ResultEnum.SYSTEMERROR.getMessage(), null);
	}
	/**
	 * 分页条件查询
	 */
	@Override
	public ResultVO findBanner(Map<String, Object> paramMap) {
		try {
			if(paramMap.get("curryPage")==null || paramMap.get("curryPage").equals("")){
				paramMap.put("curryPage", CommonConstants.DEFAULT_CURRY_PAGE);
			}
			if(paramMap.get("size")==null || paramMap.get("size").equals("")){
				paramMap.put("size", CommonConstants.DEFAULT_SIZE);
			}
			Page<YuekejuAppBannerEntity> page = new Page<YuekejuAppBannerEntity>(Integer.parseInt(paramMap.get("curryPage").toString()), Integer.parseInt(paramMap.get("size").toString()));
			List<YuekejuAppBannerEntity> findBannerBySeach = baseMapper.findBannerBySeach(paramMap);
			if(findBannerBySeach!=null && !findBannerBySeach.isEmpty()){
				Page<YuekejuAppBannerEntity> setRecords = page.setRecords(findBannerBySeach);
				return  new ResultVO(ResultEnum.SELECTSUCCESS.getCode(),CommonConstants.TRUE,CommonConstants.BANNER_NAME+ResultEnum.SELECTSUCCESS.getMessage(), setRecords);
			}
			return  new ResultVO(ResultEnum.FINDNULLERROR.getCode(),CommonConstants.FALSE,CommonConstants.BANNER_NAME+ResultEnum.FINDNULLERROR.getMessage(), null);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  new ResultVO(ResultEnum.SYSTEMERROR.getCode(),CommonConstants.FALSE,ResultEnum.SYSTEMERROR.getMessage(), null);
	}

	
	
	
}
