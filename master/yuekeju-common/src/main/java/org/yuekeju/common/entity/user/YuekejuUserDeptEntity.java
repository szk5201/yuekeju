package org.yuekeju.common.entity.user;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

import org.yuekeju.common.vo.YuekejuPersionLiableVO;

/**
 * <p>
 * 组织机构用户中间表
 * </p>
 *
 * @author suzk123
 * @since 2020-07-09
 */
@TableName("yuekeju_user_dept")
@Data
@EqualsAndHashCode(callSuper = true)
public class YuekejuUserDeptEntity   extends YuekejuPersionLiableVO implements Serializable {

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
     * 组织机构id
     */
    @TableField("dept_id")
    private String deptId;
    /**
     * 用户id
     */
    @TableField("user_id")
    private String userId;

}
