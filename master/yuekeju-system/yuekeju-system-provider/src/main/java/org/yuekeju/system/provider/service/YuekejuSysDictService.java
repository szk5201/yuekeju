package org.yuekeju.system.provider.service;

import com.baomidou.mybatisplus.service.IService;
import org.yuekeju.common.entity.system.YuekejuSysDictEntity;
import org.yuekeju.common.vo.ResultVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 字典表 服务类
 * </p>
 *
 * @author suzk123
 * @since 2020-07-09
 */
public interface YuekejuSysDictService extends IService<YuekejuSysDictEntity> {
    /**
     * 查询字典
     *
     * @param params
     * @return
     */
    ResultVO findAllDictSearch(Map<String, Object> params);

    /**
     * 新增字典
     *
     * @param yuekejuSysDictEntity
     * @return
     */
    ResultVO insertDictSearch(YuekejuSysDictEntity yuekejuSysDictEntity);

    /**
     * 删除字典
     *
     * @param ids
     * @return
     */
    ResultVO deleteDictSearch(List<String> ids);

    /**
     * 修改字典信息
     *
     * @param yuekejuSysDictEntity
     * @return
     */
    ResultVO updateDictSearch(YuekejuSysDictEntity yuekejuSysDictEntity);

    /**
     * 根据code 查询
     *
     * @param yuekejuCode
     * @return
     */
    ResultVO findDictSearchById(String yuekejuCode);
}
