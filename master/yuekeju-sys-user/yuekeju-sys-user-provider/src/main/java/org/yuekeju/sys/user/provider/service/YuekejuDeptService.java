package org.yuekeju.sys.user.provider.service;

import com.baomidou.mybatisplus.service.IService;
import org.yuekeju.common.entity.user.YuekejuDeptEntity;
import org.yuekeju.common.vo.ResultVO;

import java.util.Map;

/**
 * <p>
 * 组织机构表 服务类
 * </p>
 *
 * @author suzk123
 * @since 2020-07-09
 */
public interface YuekejuDeptService extends IService<YuekejuDeptEntity> {
    /**
     * 查询table数据
     *
     * @param params
     * @return
     */
    ResultVO findSearchAll(Map<String, Object> params);

    /**
     * 查询组织机构树
     *
     * @param params
     * @return
     */
    ResultVO findSearchTreeAll(Map<String, Object> params);

    /**
     * 新增/修改组织机构
     *
     * @param yuekejuDeptEntity
     * @return
     */
    ResultVO insertDept(YuekejuDeptEntity yuekejuDeptEntity);

    /**
     * 批量删除
     *
     * @param yuekejuCode
     * @return
     */
    ResultVO deleteDpet(String[] yuekejuCode);

    /**
     * 判断中文名和英文名和code码是否重复
     *
     * @param yuekejuDeptEntity
     * @return
     */
    ResultVO isYueKejuCodeAndEnAndCn(YuekejuDeptEntity yuekejuDeptEntity);
}
