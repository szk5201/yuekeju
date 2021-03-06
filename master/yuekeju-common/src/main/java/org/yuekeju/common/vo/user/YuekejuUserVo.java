package org.yuekeju.common.vo.user;

import lombok.Data;
import org.yuekeju.common.vo.YuekejuPersionLiableVO;

import java.io.Serializable;
import java.util.Date;

@Data
public class YuekejuUserVo extends YuekejuPersionLiableVO implements Serializable {
    private static final long serialVersionUID = 777619152631619611L;
    private int id;
    /**
     * 唯一标识
     */
    private String yuekejuCode;
    /**
     * 登陆名
     */
    private String loginName;
    /**
     * 名称
     */
    private String name;
    /**
     *
     */
    private String userPassword;
    /**
     * 地址
     */
    private String userAdress;
    /**
     * 性别
     */
    private int sex;
    /**
     * 用户号
     */
    private String userNumber;
    /**
     * 密码加盐
     */
    private String salf;
    /**
     * 删除状态
     */
    private int delTabStatus;
    /**
     * 禁用启用状态
     */
    private int disableStatus;
    /**
     * 最后一次登陆时间
     */
    private Date lastLoginTime;
    /**
     * 锁定状态
     */
    private int userLockStatus;
    /**
     * 锁定次数
     */
    private int userLockCount;
    /**
     * 锁定时间
     */
    private Date userLockTime;
    /**
     * 排序
     */
    private String order;
    /**
     * 当前页
     */
    private int currentPage;
    /**
     * 每页显示行数
     */
    private int pageSize;

    private int loginType;
}
