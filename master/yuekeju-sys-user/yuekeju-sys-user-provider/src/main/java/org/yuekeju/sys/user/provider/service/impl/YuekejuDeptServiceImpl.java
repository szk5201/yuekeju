package org.yuekeju.sys.user.provider.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yuekeju.common.constants.CommonConstants;
import org.yuekeju.common.entity.user.YuekejuDeptEntity;
import org.yuekeju.common.util.InsertDataUtil;
import org.yuekeju.common.vo.ResultEnum;
import org.yuekeju.common.vo.ResultVO;
import org.yuekeju.sys.user.provider.dao.YuekejuDeptDAO;
import org.yuekeju.sys.user.provider.service.YuekejuDeptService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 组织机构表 服务实现类
 * </p>
 *
 * @author suzk123
 * @since 2020-07-09
 */
@Service
@Slf4j
public class YuekejuDeptServiceImpl extends ServiceImpl<YuekejuDeptDAO, YuekejuDeptEntity> implements YuekejuDeptService {
    /**
     * 查询所有数据
     *
     * @param params
     * @return
     */
    @Override
    public ResultVO findSearchAll(Map<String, Object> params) {
        try {
            EntityWrapper entityWrapper = new EntityWrapper();
            if (params.get("currentPage") == null || params.get("currentPage").toString().equals("")) {
                params.put("currentPage", 1);
            }
            if (params.get("pageSize") == null || params.get("pageSize").toString().equals("")) {
                params.put("pageSize", 20);
            }
           /* if (params.get("deptParentId") != null && !params.get("deptParentId").toString().equals("")) {
                if (("-1").equals(params.get("deptParentId").toString())) {
                    params.put("deptParentId", "");
                }
            }*/
            Page<YuekejuDeptEntity> page = new Page<YuekejuDeptEntity>(Integer.parseInt(params.get("currentPage").toString()), Integer.parseInt(params.get("pageSize").toString()));
            Integer searchTotal = baseMapper.findSearchTotal(params);
            List<YuekejuDeptEntity> searchAll = baseMapper.findSearchAll(page, params);
            for (YuekejuDeptEntity deptEntity : searchAll) {
                if (deptEntity.getDeptDisable() == 0) {
                    deptEntity.setDeptDisableSwitch(false);
                } else {
                    deptEntity.setDeptDisableSwitch(true);
                }
            }
            page.setRecords(searchAll);
            page.setTotal(searchTotal);
            return new ResultVO(ResultEnum.SELECTSUCCESS.getCode(), CommonConstants.TRUE, ResultEnum.SELECTSUCCESS.getMessage(), page);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultVO(ResultEnum.SYSTEMERROR.getCode(), CommonConstants.FALSE, ResultEnum.SYSTEMERROR.getMessage(), null);
    }

    /**
     * 查询组织机构树
     *
     * @param params
     * @return
     */
    @Override
    public ResultVO findSearchTreeAll(Map<String, Object> params) {
        try {
            EntityWrapper entityWrapper = new EntityWrapper();
            if (params.get("deptParentId") != null && !("").equals(params.get("deptParentId"))) {
                entityWrapper.eq("dept_parent_id", params.get("deptParentId").toString());
            } else {
                entityWrapper.eq("dept_parent_id", "-1");
            }
            entityWrapper.orderBy("dept_order_num");
            List<YuekejuDeptEntity> list = baseMapper.selectList(entityWrapper);
            recursionList(list);
            return new ResultVO(ResultEnum.SELECTSUCCESS.getCode(), CommonConstants.TRUE, ResultEnum.SELECTSUCCESS.getMessage(), list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultVO(ResultEnum.SYSTEMERROR.getCode(), CommonConstants.FALSE, ResultEnum.SYSTEMERROR.getMessage(), null);
    }

    /**
     * 新增组织机构和修改组织机构
     *
     * @param yuekejuDeptEntity
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO insertDept(YuekejuDeptEntity yuekejuDeptEntity) {
        ResultVO repate = isRepate(yuekejuDeptEntity);
        if (!repate.getIsSuccess()) {
            return repate;
        }
        /**
         * 判断是新增还是修改
         */
        if (yuekejuDeptEntity.getYuekejuCode() == null || yuekejuDeptEntity.getYuekejuCode().equals("")) {
            InsertDataUtil.createData(yuekejuDeptEntity);
            Integer insert = baseMapper.insert(yuekejuDeptEntity);
            return new ResultVO(ResultEnum.INSERTSUCCESS.getCode(), CommonConstants.TRUE, ResultEnum.INSERTSUCCESS.getMessage(), null);
        } else {
            EntityWrapper entityWrapper = new EntityWrapper();
            entityWrapper.eq("yuekeju_code", yuekejuDeptEntity.getYuekejuCode());
            List<YuekejuDeptEntity> list = baseMapper.selectList(entityWrapper);
            if (list != null && !list.isEmpty()) {
                if (list.get(0).getUpdateTime().getTime() == yuekejuDeptEntity.getUpdateTime().getTime()) {
                    InsertDataUtil.updateData(yuekejuDeptEntity);
                    Integer update = baseMapper.update(yuekejuDeptEntity, entityWrapper);
                    return new ResultVO(ResultEnum.UPDATESUCCESS.getCode(), CommonConstants.TRUE, ResultEnum.UPDATESUCCESS.getMessage(), null);
                } else {
                    return new ResultVO(ResultEnum.UPDATETIMEERROR.getCode(), CommonConstants.FALSE, ResultEnum.UPDATETIMEERROR.getMessage(), null);
                }

            } else {
                return new ResultVO(ResultEnum.UPDATEDELETE.getCode(), CommonConstants.FALSE, ResultEnum.UPDATEDELETE.getMessage(), null);
            }

        }


    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO deleteDpet(String[] yuekejuCode) {
        List<String> yuekejuCodeList = new ArrayList<>();
        for (int i = 0; i < yuekejuCode.length; i++) {
            yuekejuCodeList.add(yuekejuCode[i]);
        }
        recursionDelete(yuekejuCodeList);

        return new ResultVO(ResultEnum.DELETESUCCESS.getCode(), CommonConstants.TRUE, ResultEnum.DELETESUCCESS.getMessage(), null);
    }

    @Override
    public ResultVO isYueKejuCodeAndEnAndCn(YuekejuDeptEntity yuekejuDeptEntity) {
        return isRepate(yuekejuDeptEntity);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO disableFalseAndTrue(YuekejuDeptEntity yuekejuDeptEntity) {
        baseMapper.updateDept(yuekejuDeptEntity);
        return new ResultVO(ResultEnum.UPDATESUCCESS.getCode(), CommonConstants.TRUE, ResultEnum.UPDATESUCCESS.getMessage(), null);
    }

    /**
     * 校验查询
     *
     * @param yuekejuDeptEntity
     * @return
     */
    private ResultVO isRepate(YuekejuDeptEntity yuekejuDeptEntity) {

        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("dept_code", yuekejuDeptEntity.getDeptCode());
        if (yuekejuDeptEntity.getYuekejuCode() != null && !"".equals(yuekejuDeptEntity.getYuekejuCode())) {
            entityWrapper.ne("yuekeju_code", yuekejuDeptEntity.getYuekejuCode());
        }
        Integer selectCount = baseMapper.selectCount(entityWrapper);
        if (selectCount > 0) {
            return new ResultVO(ResultEnum.INSERTDEPTCODE.getCode(), CommonConstants.FALSE, ResultEnum.INSERTDEPTCODE.getMessage(), null);
        }
        entityWrapper = new EntityWrapper();
        entityWrapper.eq("dept_en_name", yuekejuDeptEntity.getDeptEnName());
        if (yuekejuDeptEntity.getYuekejuCode() != null && !"".equals(yuekejuDeptEntity.getYuekejuCode())) {
            entityWrapper.ne("yuekeju_code", yuekejuDeptEntity.getYuekejuCode());
        }
        selectCount = baseMapper.selectCount(entityWrapper);
        if (selectCount > 0) {
            return new ResultVO(ResultEnum.INSERTREPEATEN.getCode(), CommonConstants.FALSE, ResultEnum.INSERTREPEATEN.getMessage(), null);
        }
        entityWrapper = new EntityWrapper();
        entityWrapper.eq("dept_cn_name", yuekejuDeptEntity.getDeptCnName());
        if (yuekejuDeptEntity.getYuekejuCode() != null && !"".equals(yuekejuDeptEntity.getYuekejuCode())) {
            entityWrapper.ne("yuekeju_code", yuekejuDeptEntity.getYuekejuCode());
        }
        selectCount = baseMapper.selectCount(entityWrapper);
        if (selectCount > 0) {
            return new ResultVO(ResultEnum.INSERTREPEATCN.getCode(), CommonConstants.FALSE, ResultEnum.INSERTREPEATCN.getMessage(), null);
        }
        entityWrapper = new EntityWrapper();
        if (yuekejuDeptEntity.getYuekejuCode() == null || "".equals(yuekejuDeptEntity.getYuekejuCode())) {
            if (yuekejuDeptEntity.getDeptParentId() != null && !"".equals(yuekejuDeptEntity.getDeptParentId())) {
                entityWrapper.eq("yuekeju_code", yuekejuDeptEntity.getDeptParentId());
                List<YuekejuDeptEntity> selectList = baseMapper.selectList(entityWrapper);
                if (selectList != null && !selectList.isEmpty()) {
                    if (selectList.get(0).getIsLeaf() == 1) {
                        return new ResultVO(ResultEnum.INSERTLEAF.getCode(), CommonConstants.FALSE, ResultEnum.INSERTLEAF.getMessage(), null);
                    }
                } else {
                    return new ResultVO(ResultEnum.INSERTPARENTNOT.getCode(), CommonConstants.FALSE, ResultEnum.INSERTPARENTNOT.getMessage(), null);
                }
            }
        } else {
            entityWrapper.eq("dept_parent_id", yuekejuDeptEntity.getYuekejuCode());
            List<YuekejuDeptEntity> selectList = baseMapper.selectList(entityWrapper);
            if (selectList != null && !selectList.isEmpty()) {
                if (selectList.get(0).getIsLeaf() != yuekejuDeptEntity.getIsLeaf()) {
                    return new ResultVO(ResultEnum.UPDATETLEAF.getCode(), CommonConstants.FALSE, ResultEnum.UPDATETLEAF.getMessage(), null);
                }
            }
        }

        return new ResultVO(ResultEnum.SELECTSUCCESS.getCode(), CommonConstants.TRUE, ResultEnum.SELECTSUCCESS.getMessage(), null);
    }

    /**
     * 递归查询组织机构
     *
     * @param list
     */
    private void recursionList(List<YuekejuDeptEntity> list) {
        if (list != null && !list.isEmpty()) {
            for (YuekejuDeptEntity dept : list) {
                EntityWrapper entityWrapper = new EntityWrapper();
                entityWrapper.eq("dept_parent_id", dept.getYuekejuCode());
                List<YuekejuDeptEntity> selectList = baseMapper.selectList(entityWrapper);
                if (selectList != null && !selectList.isEmpty()) {
                    recursionList(selectList);
                }
                dept.setChilder(selectList);
            }
        }
    }

    /**
     * 递归删除组织机构
     *
     * @param yuekejuCode
     */
    private void recursionDelete(List<String> yuekejuCode) {
        if (yuekejuCode != null && !yuekejuCode.isEmpty()) {
            EntityWrapper entityWrapper = new EntityWrapper();
            entityWrapper.in("yuekeju_code", yuekejuCode);
            Integer delete = baseMapper.delete(entityWrapper);
            entityWrapper = new EntityWrapper();
            entityWrapper.in("dept_parent_id", yuekejuCode);
            entityWrapper.setSqlSelect("yuekeju_code");
            List<String> list = baseMapper.selectList(entityWrapper);
            if (list != null && !list.isEmpty()) {
                recursionDelete(list);
            }
        }
    }
}
