package org.yuekeju.common.entity.user;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.yuekeju.common.vo.YuekejuPersionLiableVO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 组织机构表
 * </p>
 *
 *
 * @author suzk123
 * @since 2020-07-09
 */
@TableName("yuekeju_dept")
@EqualsAndHashCode(callSuper = true)
@Data
public class YuekejuDeptEntity  extends YuekejuPersionLiableVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 唯一标识
     */
    @TableField(value = "yuekeju_code", fill = FieldFill.INSERT)
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
    @TableField("is_leaf")
    private short isLeaf;
    @TableField(exist = false)
    private List<YuekejuDeptEntity> childer;
    @TableField(exist = false)
    private String parentName;
}
