package org.yuekeju.common.entity.user;

import java.io.Serializable;

import org.yuekeju.common.vo.YuekejuPersionLiableVO;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 角色权限关系表
 * </p>
 *
 * @author suzk123
 * @since 2020-07-09
 */
@TableName("yuekeju_role_permission")
@Data
@EqualsAndHashCode(callSuper = true)
public class YuekejuRolePermissionEntity  extends YuekejuPersionLiableVO  implements Serializable {

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
     * 角色id
     */
    @TableField("role_id")
    private String roleId;
    /**
     * 权限id
     */
    @TableField("permission_id")
    private String permissionId;
}
