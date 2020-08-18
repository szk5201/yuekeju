package org.yuekeju.sys.user.service.feigin.impl;

import org.springframework.stereotype.Component;
import org.yuekeju.common.entity.user.YuekejuRoleEntity;
import org.yuekeju.common.vo.ResultEnum;
import org.yuekeju.common.vo.ResultVO;
import org.yuekeju.sys.user.service.feigin.YuekejuRoleFeiginService;

import java.util.Map;

@Component
public class YuekejuRoleFeiginServiceImpl implements YuekejuRoleFeiginService {

    @Override
    public ResultVO insertRole(YuekejuRoleEntity yuekejuRoleEntity) {
        return new ResultVO(ResultEnum.CONNECTFEIGIN.getCode(), false, ResultEnum.CONNECTFEIGIN.getMessage(), null);
    }

    @Override
    public ResultVO findAllBySearch(Map<String, Object> param) {
        return new ResultVO(ResultEnum.CONNECTFEIGIN.getCode(), false, ResultEnum.CONNECTFEIGIN.getMessage(), null);
    }
}
