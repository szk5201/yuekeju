package org.yuekeju.common.entity.user;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.yuekeju.common.vo.YuekejuPersionLiableVO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author suzk123
 * @since 2020-07-09
 */
@TableName("yuekeju_role")
@EqualsAndHashCode(callSuper = true)
@Data
public class YuekejuRoleEntity  extends YuekejuPersionLiableVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type=IdType.AUTO)
    private Long  id;
    /**
     * 唯一标识
     */
    @TableField("yuekeju_code")
    private String yuekejuCode;
    /**
     * 角色英文名称
     */
    @TableField("role_en_name")
    private String roleEnName;
    /**
     * 角色中文名称
     */
    @TableField("role_cn_name")
    private String roleCnName;
    /**
     * 角色描述
     */
    @TableField("role_description")
    private String roleDescription;
    /**
     * 角色状态
     */
    @TableField("role_status")
    private BigDecimal roleStatus;

    @TableField(exist = false)
    private List<YuekejuRolePermissionEntity> rolePermisionList;
   
}
