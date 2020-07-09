package org.yuekeju.common.entity.user;

import java.math.BigDecimal;
import java.util.Date;

import org.yuekeju.common.vo.YuekejuPersionLiableVO;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 组织机构表
 * </p>
 *
 * @author suzk123
 * @since 2020-07-09
 */
@TableName("yuekeju_dept")
@Data
public class YuekejuDeptEntity  extends YuekejuPersionLiableVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;
    /**
     * 唯一标识
     */
    @TableField("yuekeju_code")
    private String yuekejuCode;
    /**
     * 组织机构英文名称
     */
    @TableField("dept_en_name")
    private String deptEnName;
    /**
     * 组织机构中文名称
     */
    @TableField("dept_cn_name")
    private String deptCnName;
    /**
     * 上级组织机构id
     */
    @TableField("dept_parent_id")
    private String deptParentId;
    /**
     * 组织机构排序
     */
    @TableField("dept_order_num")
    private BigDecimal deptOrderNum;
    /**
     * 组织机构是否启用
     */
    @TableField("dept_disable")
    private BigDecimal deptDisable;
    /**
     * 组织机构描述
     */
    @TableField("dept_description")
    private String deptDescription;
    /**
     * 组织机构code码
     */
    @TableField("dept_code")
    private String deptCode;
    /**
     * 删除标记
     */
    @TableField("del_tab_status")
    private BigDecimal delTabStatus;


}
