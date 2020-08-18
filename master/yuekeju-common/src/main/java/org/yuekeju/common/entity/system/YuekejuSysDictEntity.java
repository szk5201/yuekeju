package org.yuekeju.common.entity.system;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.yuekeju.common.vo.YuekejuPersionLiableVO;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 字典表
 *
 * @author suzk123
 * @since 2020-07-09
 */
@TableName("yuekeju_sys_dict")
@EqualsAndHashCode(callSuper = true)
@Data
public class YuekejuSysDictEntity  extends YuekejuPersionLiableVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;
    /**
     * 父id
     */
    private Long pid;
    /**
     * 英文编码
     */
    @TableField("yuekeju_en_code")
    private String yuekejuEnCode;
    /**
     * 中文编码
     */
    @TableField("yuekeju_cn_code")
    private String yuekejuCnCode;
    /**
     * 数据
     */
    @TableField("yuekeju_date")
    private String yuekejuDate;
    /**
     * 顺序
     */
    @TableField("yuekeju_number")
    private BigDecimal yuekejuNumber;
    /**
     * 是否是系统字典
     */
    @TableField("yuekeju_sys_status")
    private BigDecimal yuekejuSysStatus;
    /**
     * 删除标记
     */
    @TableField("del_tab_status")
    private BigDecimal delTabStatus;
    /**
     * 描述
     */
    @TableField("yuekeju_desc")
    private String yuekejuDesc;
}
