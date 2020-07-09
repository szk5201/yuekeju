package org.yuekeju.common.entity.user;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import lombok.Data;

import java.io.Serializable;

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
public class YuekejuUserDeptEntity implements Serializable {

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

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "YuekejuUserDept{" +
        ", id=" + id +
        ", yuekejuCode=" + yuekejuCode +
        ", deptId=" + deptId +
        ", userId=" + userId +
        "}";
    }
}
