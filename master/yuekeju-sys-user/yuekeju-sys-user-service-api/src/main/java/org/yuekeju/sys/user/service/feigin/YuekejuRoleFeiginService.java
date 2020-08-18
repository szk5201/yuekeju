package org.yuekeju.sys.user.service.feigin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.yuekeju.common.auth.AuthSecurityAnnotation;
import org.yuekeju.common.entity.user.YuekejuRoleEntity;
import org.yuekeju.common.vo.ResultVO;
import org.yuekeju.sys.user.service.feigin.impl.YuekejuRoleFeiginServiceImpl;

import java.util.Map;

@FeignClient(value = "yuekeju-sys-user", fallback = YuekejuRoleFeiginServiceImpl.class)
public interface YuekejuRoleFeiginService {
    /**
     * 新增角色
     *
     * @param yuekejuRoleEntity
     * @return
     */
    @PostMapping("/v1/yuekejuRole/insertRole")
    @AuthSecurityAnnotation(isAuth = true, perms = "role:insert")
    ResultVO insertRole(@RequestBody YuekejuRoleEntity yuekejuRoleEntity);

    /**
     * 全查 分页 和条件查询
     *
     * @param param
     * @return
     */
    @GetMapping("/v1/yuekejuRole/findAllBySearch")
    @AuthSecurityAnnotation(isAuth = true, perms = "role:findAllBySearch")
    public ResultVO findAllBySearch(@RequestParam Map<String, Object> param);

}
