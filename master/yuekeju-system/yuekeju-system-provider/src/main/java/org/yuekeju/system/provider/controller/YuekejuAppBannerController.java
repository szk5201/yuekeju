package org.yuekeju.system.provider.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yuekeju.common.auth.AuthSecurityAnnotation;
import org.yuekeju.common.entity.system.YuekejuAppBannerEntity;
import org.yuekeju.common.vo.ResultVO;
import org.yuekeju.system.provider.service.YuekejuAppBannerService;
/**
 * 轮播图交互层
 * @author szk  2020年7月16日 15:10:52
 *
 */
@RequestMapping("/banner")
@RestController
public class YuekejuAppBannerController {
	@Autowired
	private YuekejuAppBannerService yuekejuAppBannerService;
	
	/**
	 * 查询
	 * @param paramMap
	 * @return
	 */
	
	@SuppressWarnings("all")
	@AuthSecurityAnnotation(isAuth=true,perms="ddd")
	@RequestMapping("/findBannerBySearch")
	public ResultVO findBannerBySearch(Map<String,Object> paramMap){
		
		return yuekejuAppBannerService.findBanner(paramMap);
	}
	/**
	 * 新增
	 * @param yuekejuAppBannerEntity
	 * @return
	 */
	@SuppressWarnings("all")
	@RequestMapping("/insertBanner")
	public ResultVO insertBanner(YuekejuAppBannerEntity yuekejuAppBannerEntity){
		return yuekejuAppBannerService.insertBanner(yuekejuAppBannerEntity);
	}
	/**
	 * 删除查询
	 * @param id
	 * @return
	 */
	@SuppressWarnings("all")
	@RequestMapping("/findBannerById/{id}")
	public ResultVO findBannerById(@PathVariable Integer id){
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("id", id);
		return yuekejuAppBannerService.findBanner(param);
	}
	/**
	 * 删除
	 * @param ids
	 * @return
	 */
	@SuppressWarnings("all")
	@RequestMapping("/deleteBanner")
	public ResultVO deleteBanner(List<Integer> ids){
		
		return yuekejuAppBannerService.deleteBanner(ids);
	}
	
	
	
	
}
