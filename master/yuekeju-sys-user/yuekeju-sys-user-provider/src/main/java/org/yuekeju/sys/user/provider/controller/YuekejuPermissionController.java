package org.yuekeju.sys.user.provider.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yuekeju.common.auth.AuthSecurityAnnotation;
import org.yuekeju.common.vo.ResultVO;
import org.yuekeju.sys.user.provider.service.YuekejuPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 权限表 前端控制器
 * </p>
 *
 * @author suzk123
 * @since 2020-07-20
 */
@RestController
@SuppressWarnings("all")
@RequestMapping("/yuekejuPermission")
public class YuekejuPermissionController {
	@Autowired
	private YuekejuPermissionService yuekejuPermissionService;
	/**
	 * 查询所有权限
	 * @return
	 */
	@GetMapping("/findAllPermission")
	@AuthSecurityAnnotation(isAuth=true,perms="yuekejuPermission:findAllPermission")
	public ResultVO findAllPermission(){
		return yuekejuPermissionService.findAllPermission(null);
		
	}
	
	
	
	
	
	
}

