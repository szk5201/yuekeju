package org.yuekeju.sys.user.provider.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.yuekeju.common.auth.AuthSecurityAnnotation;
import org.yuekeju.common.constants.CommonConstants;
import org.yuekeju.common.entity.user.YuekejuRoleEntity;
import org.yuekeju.common.vo.ResultVO;
import org.yuekeju.sys.user.provider.service.YuekejuRoleService;

import java.util.Map;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author suzk123
 * @since 2020-07-09
 */
@RestController
@RequestMapping(CommonConstants.VERSION_CONTROLLER + "/yuekejuRole")
@SuppressWarnings("all")
public class YuekejuRoleController {
	@Autowired
	private YuekejuRoleService yuekejuRoleService;
	/**
	 * 新增角色
	 * @param yuekejuRoleEntity
	 * @return
	 */
	@PostMapping("/insertRole")
	@AuthSecurityAnnotation(isAuth=true,perms="role:insert")
	public ResultVO insertRole(@RequestBody YuekejuRoleEntity yuekejuRoleEntity, @RequestHeader String tokenId) {
		return yuekejuRoleService.insertRole(yuekejuRoleEntity, tokenId);
	}
	/**
	 * 全查 分页 和条件查询
	 * @param param
	 * @return
	 */
	@GetMapping("/findAllBySearch")
	@AuthSecurityAnnotation(isAuth=true,perms="role:findAllBySearch")
	public ResultVO findAllBySearch(@RequestParam Map<String, Object> param, @RequestHeader String tokenId) {
		return yuekejuRoleService.findAllBySearch(param, tokenId);
	}
	/**
	 * 全查不分页分页 和条件查询 例如id查询
	 * @param param
	 * @return
	 */
	@GetMapping("/findRoleByCode")
	@AuthSecurityAnnotation(isAuth=true,perms="role:findRoleByCode")
	public ResultVO findRoleByCode(Map<String, Object> param, @RequestHeader String tokenId) {
		return yuekejuRoleService.findRoleByCode(param, tokenId);
	}
	/**
	 * 验证 中文名称和英文名称
	 */
	@GetMapping("/findRoleNameByCnAndEn")
	@AuthSecurityAnnotation(isAuth=true,perms="role:findRoleNameByCnAndEn")
	public ResultVO findRoleNameByCnAndEn(YuekejuRoleEntity yuekejuRoleEntity, @RequestHeader String tokenId) {
		return yuekejuRoleService.findRoleNameByCnAndEn(yuekejuRoleEntity, tokenId);
		
	}

	@PostMapping("/deleteRoleByCode")
	@AuthSecurityAnnotation(isAuth = true, perms = "role:findRoleNameByCnAndEn")
	public ResultVO deleteRoleByCode(@RequestBody String ids[], @RequestHeader String tokenId) {
		return yuekejuRoleService.deleteRole(ids, tokenId);
	}
}

