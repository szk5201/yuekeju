package org.yuekeju.sys.user.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.yuekeju.common.constants.CommonConstants;
import org.yuekeju.common.entity.user.YuekejuRoleEntity;
import org.yuekeju.common.vo.ResultVO;
import org.yuekeju.sys.user.service.feigin.YuekejuRoleFeiginService;

import java.util.Map;

@RestController
@RequestMapping(CommonConstants.VERSION_CONTROLLER_API + "/roleFeigin")
public class YuekejuRoleFeiginController {
    @Autowired
    YuekejuRoleFeiginService yuekejuRoleFeiginService;

    @PostMapping("/insertRole")
    public ResultVO insertRole(YuekejuRoleEntity yuekejuRoleEntity) {
        return yuekejuRoleFeiginService.insertRole(yuekejuRoleEntity);
    }

    @GetMapping("/findAllBySearch")
    public ResultVO findAllBySearch(@RequestParam Map<String, Object> param) {
        return yuekejuRoleFeiginService.findAllBySearch(param);
    }
}
