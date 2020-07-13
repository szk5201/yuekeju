package org.yuekeju.common.entity.user;

import java.util.Date;

import org.yuekeju.common.vo.YuekejuPersionLiableVO;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 用户登录密码历史表
 * </p>
 *
 * @author suzk123
 * @since 2020-07-09
 */
@TableName("yuekeju_user_loginpassword")
@Data
public class YuekejuUserLoginpasswordEntity  extends YuekejuPersionLiableVO implements Serializable {

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
     * 用户登录密码
     */
    @TableField("user_password")
    private String userPassword;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;


   
}
