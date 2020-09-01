package org.yuekeju.sys.user.provider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.yuekeju.common.auth.AuthSecurityAnnotation;
import org.yuekeju.common.constants.CommonConstants;
import org.yuekeju.common.entity.user.UserEntity;
import org.yuekeju.common.vo.ResultVO;
import org.yuekeju.common.vo.user.YuekejuUserVo;
import org.yuekeju.sys.user.provider.service.IUserService;

/**
 * @author szk
 * @description 用户访问层
 * @date 2020/8/27
 */
@RestController
@RequestMapping(CommonConstants.VERSION_CONTROLLER + "/manage/user")
public class YuekejuUserController {
    @Autowired
    private IUserService iUserService;

    /**
     * 分页查询/条件查询用户
     *
     * @param yuekejuUserVo
     * @return
     * @RequestParam YuekejuUserVo yuekejuUserVo
     */
    @GetMapping("/findUserAllBySearch")
    @AuthSecurityAnnotation(isAuth = true, perms = "user:findUserAllBySearch")
    public ResultVO findUserAllBySearch(YuekejuUserVo yuekejuUserVo) {

        return iUserService.findUserAllBySearch(yuekejuUserVo);
    }

    /**
     * 修改用户信息
     *
     * @param userEntity
     * @return
     */
    @PostMapping("/updateUser")
    @AuthSecurityAnnotation(isAuth = true, perms = "user:updateUser")
    public ResultVO updateUser(@RequestBody UserEntity userEntity) {
        return iUserService.updateUser(userEntity);
    }

    /**
     * 删除用户信息 批量
     *
     * @param yuekejuCode
     * @return
     */
    @PostMapping("/yuekejuCode")
    @AuthSecurityAnnotation(isAuth = true, perms = "user:yuekejuCode")
    public ResultVO deleteUser(@RequestBody String[] yuekejuCode) {
        return iUserService.deleteUser(yuekejuCode);
    }

    /**
     * 新增用户信息
     *
     * @param userEntity
     * @return
     */
    @PostMapping("/insertUser")
    @AuthSecurityAnnotation(isAuth = true, perms = "user:insertUser")
    public ResultVO insertUser(@RequestBody UserEntity userEntity) {
        return iUserService.insertUser(userEntity);
    }

    /**
     * 修改锁定状态 和禁用状态
     *
     * @param userEntity
     * @return
     */
    @PostMapping("/updateDisableAndLock")
    @AuthSecurityAnnotation(isAuth = true, perms = "user:updateDisableAndLock")
    public ResultVO updateDisableAndLock(@RequestBody UserEntity userEntity) {
        return iUserService.updateDisableAndLock(userEntity);
    }

    /**
     * 根据code查询详情
     *
     * @param yuekejuCode
     * @return
     */
    @GetMapping("/findInfoById/{yuekejuCode}")
    @AuthSecurityAnnotation(isAuth = true, perms = "user:findInfoById")
    public ResultVO findInfoById(@PathVariable String yuekejuCode) {
        YuekejuUserVo yuekejuUserVo = new YuekejuUserVo();
        yuekejuUserVo.setYuekejuCode(yuekejuCode);
        return iUserService.findUserAllBySearch(yuekejuUserVo);
    }
}