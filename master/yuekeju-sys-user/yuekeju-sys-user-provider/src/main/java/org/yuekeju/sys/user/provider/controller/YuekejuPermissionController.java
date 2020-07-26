package org.yuekeju.sys.user.provider.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yuekeju.common.auth.AuthSecurityAnnotation;
import org.yuekeju.common.entity.user.YuekejuPermissionEntity;
import org.yuekeju.common.vo.ResultVO;
import org.yuekeju.sys.user.provider.service.YuekejuPermissionService;

import feign.Param;

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
	
	/**
	 * 新增权限
	 * @param yuekejuPermissionEntity
	 * @return
	 */
	@PostMapping("/insertPermission")
	@AuthSecurityAnnotation(isAuth=true,perms="yuekejuPermission:insertPermission")
	public ResultVO insertPermission(@RequestBody YuekejuPermissionEntity yuekejuPermissionEntity){
		return yuekejuPermissionService.insertPermission(yuekejuPermissionEntity);
		
	}
	/**
	 * 
	 * 删除权限 
	 * @param id
	 * @return
	 */
	@PostMapping("/deletePermission/{id}")
	@AuthSecurityAnnotation(isAuth=true,perms="yuekejuPermission:deletePermission")
	public ResultVO deletePermission(@PathVariable("id") String id){
		return yuekejuPermissionService.deletePermission(id);
		
	}
	
	
	
	
	
}

