package org.yuekeju.common.vo.user;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.yuekeju.common.vo.YuekejuPersionLiableVO;

import com.baomidou.mybatisplus.annotations.TableId;
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
@Data
@EqualsAndHashCode(callSuper = true)
public class YuekejuPermissionVo extends YuekejuPersionLiableVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type=IdType.AUTO)
    private Long id;
    /**
     * 唯一标识
     */
    private String yuekejuCode;
    /**
     * 权限名称
     */
    private String permissionName;
    /**
     * 权限描述
     */
    private String permissionDescription;
    /**
     * 权限路径
     */
    private String permissionUrl;
    /**
     * 权限标识
     */
    private String permissionPerms;
    /**
     * 父级权限id
     */
    private String permissionParentId;
    /**
     * 权限类型  0：目录   1：菜单   2：按钮
     */
    private BigDecimal permissionType;
    /**
     * 权限排序
     */
    private BigDecimal pemissionOrderNum;
    /**
     * 权限图标
     */
    private String permissionIcon;
    /**
     * 权限状态
     */
    private BigDecimal delTabStatus;
    /**
     * 上级菜单名称
     */
    private String permissionParentName;
    
    private List<?> list;
    
    

  
}
