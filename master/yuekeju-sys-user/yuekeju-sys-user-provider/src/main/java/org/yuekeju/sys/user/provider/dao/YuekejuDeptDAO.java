package org.yuekeju.sys.user.provider.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Mapper;
import org.yuekeju.common.entity.user.YuekejuDeptEntity;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 组织机构表 Mapper 接口
 * </p>
 *
 * @author suzk123
 * @since 2020-07-09
 */
@Mapper
public interface YuekejuDeptDAO extends BaseMapper<YuekejuDeptEntity> {
    /**
     * 分页查询
     *
     * @param page
     * @param params
     * @return
     */
    List<YuekejuDeptEntity> findSearchAll(Page page, Map<String, Object> params);

    /**
     * 查询总数
     *
     * @param params
     * @return
     */
    Integer findSearchTotal(Map<String, Object> params);

    Integer updateDept(YuekejuDeptEntity yuekejuDeptEntity);

}
