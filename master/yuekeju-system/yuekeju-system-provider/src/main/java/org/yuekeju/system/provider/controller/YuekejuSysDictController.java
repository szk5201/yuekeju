package org.yuekeju.system.provider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yuekeju.common.constants.CommonConstants;
import org.yuekeju.common.vo.ResultVO;
import org.yuekeju.system.provider.service.YuekejuSysDictService;

@RequestMapping(CommonConstants.VERSION_CONTROLLER + "/sysdict")
@RestController
public class YuekejuSysDictController {
    @Autowired
    private YuekejuSysDictService yuekejuSysDictService;

    /**
     * 分页查询字典table
     *
     * @return
     */
    @RequestMapping("/findAllDictSearch")
    public ResultVO findAllDictSearch() {
        return null;
    }

}
