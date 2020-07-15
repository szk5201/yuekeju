package org.yuekeju.system.provider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yuekeju.common.entity.system.YuekejuAppBannerEntity;
import org.yuekeju.common.vo.ResultVO;
import org.yuekeju.system.provider.service.YuekejuAppBannerService;

import com.baomidou.mybatisplus.plugins.Page;

@RequestMapping("/banner")
@RestController
public class YuekejuAppBannerController {
	@Autowired
	private YuekejuAppBannerService yuekejuAppBannerService;
	
	@SuppressWarnings("all")
	@RequestMapping("/findBannerBySearch")
	public ResultVO findBannerBySearch(YuekejuAppBannerEntity yuekejuAppBannerEntity){
		ResultVO result = new ResultVO();
		Page<YuekejuAppBannerEntity> page = new Page<YuekejuAppBannerEntity>(1,10);
		Page<YuekejuAppBannerEntity> selectPage = yuekejuAppBannerService.selectPage(page);
		result.success(result);
		result.setReturnDate(selectPage);
		return result;
	}
	@SuppressWarnings("all")
	@RequestMapping("/insertBanner")
	public ResultVO insertBanner(YuekejuAppBannerEntity yuekejuAppBannerEntity){
		ResultVO result = new ResultVO();
		boolean insert = yuekejuAppBannerService.insert(yuekejuAppBannerEntity);
		
		return null;
		
	}
	
	
	
}
