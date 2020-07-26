package org.yuekeju.common.entity.user;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

import org.yuekeju.common.vo.YuekejuPersionLiableVO;

/**
 * <p>
 * 管理用户角色关系表
 * </p>
 *
 * @author suzk123
 * @since 2020-07-09
 */
@TableName("yuekeju_sys_role_user")
@Data
@EqualsAndHashCode(callSuper = true)
public class YuekejuSysRoleUserEntity  extends YuekejuPersionLiableVO implements Serializable {

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
