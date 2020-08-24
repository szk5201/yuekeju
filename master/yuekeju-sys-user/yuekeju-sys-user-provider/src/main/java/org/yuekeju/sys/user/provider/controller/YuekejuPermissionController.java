package org.yuekeju.sys.user.provider.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.yuekeju.common.auth.AuthSecurityAnnotation;
import org.yuekeju.common.constants.CommonConstants;
import org.yuekeju.common.entity.user.YuekejuPermissionEntity;
import org.yuekeju.common.vo.ResultVO;
import org.yuekeju.sys.user.provider.service.YuekejuPermissionService;

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
@RequestMapping(CommonConstants.VERSION_CONTROLLER + "/yuekejuPermission")
public class YuekejuPermissionController {
	@Autowired
	private YuekejuPermissionService yuekejuPermissionService;
	/**
	 * 查询所有权限
	 * @return
	 */
	@GetMapping("/findAllPermission")
	@AuthSecurityAnnotation(isAuth=true,perms="yuekejuPermission:findAllPermission")
	public ResultVO findAllPermission(@RequestHeader String tokenId) {
		return yuekejuPermissionService.findAllPermission(null, tokenId);
	}
	/**
	 * 查询所有权限 menu和目录
	 * @return
	 */
	@GetMapping("/findByMenuPermission")
	@AuthSecurityAnnotation(isAuth=true,perms="yuekejuPermission:findByMenuPermission")
	public ResultVO findByMenuPermission(@RequestHeader String tokenId) {
		return yuekejuPermissionService.findAllPermissionMenu(null, tokenId);
	}
	/**
	 * 新增权限
	 * @param yuekejuPermissionEntity
	 * @return
	 */
	@PostMapping("/insertPermission")
	@AuthSecurityAnnotation(isAuth=true,perms="yuekejuPermission:insertPermission")
	public ResultVO insertPermission(@RequestBody YuekejuPermissionEntity yuekejuPermissionEntity, @RequestHeader String tokenId) {
		return yuekejuPermissionService.insertPermission(yuekejuPermissionEntity, tokenId);
	}
	/**
	 * 
	 * 删除权限 
	 * @param id
	 * @return
	 */
	@PostMapping("/deletePermission")
	@AuthSecurityAnnotation(isAuth=true,perms="yuekejuPermission:deletePermission")
	public ResultVO deletePermission(@RequestBody String[] id, @RequestHeader String tokenId) {
		return yuekejuPermissionService.deletePermission(id, tokenId);
	}
	/**
	 * 根据code 查询
	 * @param yuekejuCode
	 * @return
	 */
	@GetMapping("/findByCode/{yuekejuCode}")
	public ResultVO findByCode(@PathVariable("yuekejuCode") String yuekejuCode, @RequestHeader String tokenId) {
		return yuekejuPermissionService.findByidPermission(yuekejuCode, tokenId);
	}
	
	
	
	
}

