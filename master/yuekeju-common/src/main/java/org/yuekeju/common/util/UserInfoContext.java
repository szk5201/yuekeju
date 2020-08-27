package org.yuekeju.common.util;

import org.yuekeju.common.entity.user.UserEntity;

/**
 * @author szk
 * @description 用户信息工具类
 * @date 2020/8/27
 */
public class UserInfoContext {
    private static ThreadLocal<UserEntity> userInfo = new ThreadLocal<UserEntity>();
    public static String KEY_USERINFO_IN_HTTP_HEADER = "X-AUTO-FP-USERINFO";

    public UserInfoContext() {
    }

    public static UserEntity getUser() {
        return (UserEntity) userInfo.get();
    }

    public static void setUser(UserEntity user) {
        userInfo.set(user);
    }
}
