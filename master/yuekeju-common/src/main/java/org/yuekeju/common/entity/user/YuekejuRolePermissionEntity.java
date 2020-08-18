package org.yuekeju.common.entity.user;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

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
public class YuekejuRolePermissionEntity  implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type=IdType.AUTO)
    private Long id;
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
