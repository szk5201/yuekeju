package org.yuekeju.common.entity.user;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 用户角色关系表
 * </p>
 *
 * @author suzk123
 * @since 2020-07-09
 */
@TableName("yuekeju_role_user")
@Data
public class YuekejuRoleUserEntity implements Serializable {

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
     * 用户id
     */
    @TableField("user_id")
    private String userId;
    /**
     * 角色id
     */
    @TableField("role_id")
    private String roleId;


 
}
