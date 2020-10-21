package org.yuekeju.system.provider.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.yuekeju.common.entity.system.YuekejuSysDictEntity;
import org.yuekeju.common.vo.ResultVO;
import org.yuekeju.system.provider.dao.YuekejuSysDictDAO;
import org.yuekeju.system.provider.service.YuekejuSysDictService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 字典表 服务实现类
 * </p>
 *
 * @author suzk123
 * @since 2020-07-09
 */
@Service
public class YuekejuSysDictServiceImpl extends ServiceImpl<YuekejuSysDictDAO, YuekejuSysDictEntity> implements YuekejuSysDictService {
    /**
     * 查询字典
     *
     * @param params
     * @return
     */
    @Override
    public ResultVO findAllDictSearch(Map<String, Object> params) {
        return null;
    }

    /**
     * 新增字典
     *
     * @param yuekejuSysDictEntity
     * @return
     */
    @Override
    public ResultVO insertDictSearch(YuekejuSysDictEntity yuekejuSysDictEntity) {
        return null;
    }

    /**
     * 删除字典
     *
     * @param ids
     * @return
     */
    @Override
    public ResultVO deleteDictSearch(List<String> ids) {
        return null;
    }

    /**
     * 修改字典信息
     *
     * @param yuekejuSysDictEntity
     * @return
     */
    @Override
    public ResultVO updateDictSearch(YuekejuSysDictEntity yuekejuSysDictEntity) {
        return null;
    }

    /**
     * 根据code 查询字典
     *
     * @param yuekejuCode
     * @return
     */
    @Override
    public ResultVO findDictSearchById(String yuekejuCode) {
        return null;
    }
}
