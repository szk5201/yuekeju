package org.yuekeju.sys.user.provider.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.yuekeju.common.auth.AuthSecurityAnnotation;
import org.yuekeju.common.entity.user.YuekejuRoleEntity;
import org.yuekeju.common.vo.ResultVO;
import org.yuekeju.sys.user.provider.service.YuekejuRoleService;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author suzk123
 * @since 2020-07-09
 */
@RestController
@RequestMapping("/yuekejuRole")
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
	public ResultVO  insertRole(@RequestBody YuekejuRoleEntity yuekejuRoleEntity){
		return	yuekejuRoleService.insertRole(yuekejuRoleEntity);
	}
	/**
	 * 全查 分页 和条件查询
	 * @param param
	 * @return
	 */
	@GetMapping("/findAllBySearch")
	@AuthSecurityAnnotation(isAuth=true,perms="role:findAllBySearch")
	public ResultVO  findAllBySearch(Map<String,Object> param){
		return	yuekejuRoleService.findAllBySearch(param);
	}
	/**
	 * 全查 分页 和条件查询
	 * @param param
	 * @return
	 */
	@GetMapping("/findRoleByCode")
	@AuthSecurityAnnotation(isAuth=true,perms="role:findRoleByCode")
	public ResultVO  findRoleByCode(Map<String,Object> param){
		return	yuekejuRoleService.findRoleByCode(param);
	}
}

