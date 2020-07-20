package org.yuekeju.common.vo.user;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author suzk123
 * @since 2020-07-20
 */
public class YuekejuPermissionVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type=IdType.AUTO)
    private Integer id;
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
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 创建人
     */
    private String creater;
    /**
     * 修改人
     */
    private String modified;

    
    private List<?> list;
    
    

    public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getYuekejuCode() {
        return yuekejuCode;
    }

    public void setYuekejuCode(String yuekejuCode) {
        this.yuekejuCode = yuekejuCode;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionDescription() {
        return permissionDescription;
    }

    public void setPermissionDescription(String permissionDescription) {
        this.permissionDescription = permissionDescription;
    }

    public String getPermissionUrl() {
        return permissionUrl;
    }

    public void setPermissionUrl(String permissionUrl) {
        this.permissionUrl = permissionUrl;
    }

    public String getPermissionPerms() {
        return permissionPerms;
    }

    public void setPermissionPerms(String permissionPerms) {
        this.permissionPerms = permissionPerms;
    }

    public String getPermissionParentId() {
        return permissionParentId;
    }

    public void setPermissionParentId(String permissionParentId) {
        this.permissionParentId = permissionParentId;
    }

    public BigDecimal getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(BigDecimal permissionType) {
        this.permissionType = permissionType;
    }

    public BigDecimal getPemissionOrderNum() {
        return pemissionOrderNum;
    }

    public void setPemissionOrderNum(BigDecimal pemissionOrderNum) {
        this.pemissionOrderNum = pemissionOrderNum;
    }

    public String getPermissionIcon() {
        return permissionIcon;
    }

    public void setPermissionIcon(String permissionIcon) {
        this.permissionIcon = permissionIcon;
    }

    public BigDecimal getDelTabStatus() {
        return delTabStatus;
    }

    public void setDelTabStatus(BigDecimal delTabStatus) {
        this.delTabStatus = delTabStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    @Override
    public String toString() {
        return "YuekejuPermission{" +
        ", id=" + id +
        ", yuekejuCode=" + yuekejuCode +
        ", permissionName=" + permissionName +
        ", permissionDescription=" + permissionDescription +
        ", permissionUrl=" + permissionUrl +
        ", permissionPerms=" + permissionPerms +
        ", permissionParentId=" + permissionParentId +
        ", permissionType=" + permissionType +
        ", pemissionOrderNum=" + pemissionOrderNum +
        ", permissionIcon=" + permissionIcon +
        ", delTabStatus=" + delTabStatus +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", creater=" + creater +
        ", modified=" + modified +
        "}";
    }
}
