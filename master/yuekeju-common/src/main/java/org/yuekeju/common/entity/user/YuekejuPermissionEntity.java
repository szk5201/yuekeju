package org.yuekeju.common.entity.user;

import java.io.Serializable;
import java.math.BigDecimal;

import org.yuekeju.common.vo.YuekejuPersionLiableVO;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author suzk123
 * @since 2020-07-20
 */
@TableName("yuekeju_permission")
@EqualsAndHashCode(callSuper = true)
@Data
public class YuekejuPermissionEntity extends YuekejuPersionLiableVO implements Serializable {

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
     * 权限名称
     */
    @TableField("permission_name")
    private String permissionName;
    /**
     * 权限描述
     */
    @TableField("permission_description")
    private String permissionDescription;
    /**
     * 权限路径
     */
    @TableField("permission_url")
    private String permissionUrl;
    /**
     * 权限标识
     */
    @TableField("permission_perms")
    private String permissionPerms;
    /**
     * 父级权限id
     */
    @TableField("permission_parent_id")
    private String permissionParentId;
    /**
     * 权限类型  0：目录   1：菜单   2：按钮
     */
    @TableField("permission_type")
    private BigDecimal permissionType;
    /**
     * 权限排序
     */
    @TableField("pemission_order_num")
    private BigDecimal pemissionOrderNum;
    /**
     * 权限图标
     */
    @TableField("permission_icon")
    private String permissionIcon;
    /**
     * 根id
     */
    private Long permissionRootId;
    /**
     * 权限状态
     */
    @TableField("del_tab_status")
    private BigDecimal delTabStatus;
}
