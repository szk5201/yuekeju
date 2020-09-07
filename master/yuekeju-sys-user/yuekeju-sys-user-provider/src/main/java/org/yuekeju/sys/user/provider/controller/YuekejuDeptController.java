package org.yuekeju.sys.user.provider.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.yuekeju.common.auth.AuthSecurityAnnotation;
import org.yuekeju.common.constants.CommonConstants;
import org.yuekeju.common.entity.user.YuekejuDeptEntity;
import org.yuekeju.common.vo.ResultVO;
import org.yuekeju.sys.user.provider.service.YuekejuDeptService;

import java.util.Map;

/**
 * <p>
 * 组织机构表 前端控制器
 * </p>
 *
 * @author suzk123
 * @since 2020-07-09
 */
@RestController
@RequestMapping(CommonConstants.VERSION_CONTROLLER + "/yuekejuDept")
public class YuekejuDeptController {
    @Autowired
    private YuekejuDeptService yuekejuDeptService;

    /**
     * 查询table
     *
     * @param param
     * @return
     */
    @GetMapping("/findSearchAll")
    @AuthSecurityAnnotation(isAuth = true, perms = "dept:findSearchAll")
    public ResultVO findSearchAll(@RequestParam Map<String, Object> param) {
        return yuekejuDeptService.findSearchAll(param);
    }

    /**
     * 查询树
     *
     * @param param
     * @return
     */
    @GetMapping("/findSearchAllTree")
    @AuthSecurityAnnotation(isAuth = true, perms = "dept:findSearchAllTree")
    public ResultVO findSearchAllTree(@RequestParam Map<String, Object> param) {
        return yuekejuDeptService.findSearchTreeAll(param);
    }

    /**
     * 新增修改
     *
     * @param yuekejuDeptEntity
     * @return
     */
    @PostMapping("/insertDept")
    @AuthSecurityAnnotation(isAuth = true, perms = "dept:insertDept")
    public ResultVO insertDept(@RequestBody YuekejuDeptEntity yuekejuDeptEntity) {
        return yuekejuDeptService.insertDept(yuekejuDeptEntity);
    }

    /**
     * 删除
     *
     * @param yuekejuCode
     * @return
     */
    @PostMapping("/deleteDept")
    @AuthSecurityAnnotation(isAuth = true, perms = "dept:deleteDept")
    public ResultVO deleteDept(@RequestBody String[] yuekejuCode) {
        return yuekejuDeptService.deleteDpet(yuekejuCode);
    }

    /**
     * 验证重复
     *
     * @param yuekejuDeptEntity
     * @return
     */
    @GetMapping("/isRepeat")
    @AuthSecurityAnnotation(isAuth = true, perms = "dept:isRepeat")
    public ResultVO isRepeat(YuekejuDeptEntity yuekejuDeptEntity) {
        return yuekejuDeptService.isYueKejuCodeAndEnAndCn(yuekejuDeptEntity);
    }

    /**
     * 删除
     *
     * @param yuekejuDeptEntity
     * @return
     */
    @PostMapping("/disableFalseAndTrue")
    @AuthSecurityAnnotation(isAuth = true, perms = "dept:disableFalseAndTrue")
    public ResultVO disableFalseAndTrue(@RequestBody YuekejuDeptEntity yuekejuDeptEntity) {
        return yuekejuDeptService.disableFalseAndTrue(yuekejuDeptEntity);
    }
}

