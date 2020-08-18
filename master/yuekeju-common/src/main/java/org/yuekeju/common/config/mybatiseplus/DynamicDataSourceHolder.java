package org.yuekeju.common.config.mybatiseplus;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DynamicDataSourceHolder {
    private static ThreadLocal<String> contextHolder = new ThreadLocal<String>();
    public static final String DB_MASTER = "master";
    public static final String DB_SLAVE = "slave";

    public static String getDbType() {
        String db = contextHolder.get();
        if (db == null) {
            db = DB_MASTER;
        }
        return db;
    }

    /**
     * 设置线程的dbType
     *
     * @param str
     */
    public static void setDbType(String str) {
        log.debug("所使用的数据源为：" + str);
        contextHolder.set(str);
    }

    /**
     * 清理连接类型
     */
    public static void clearDBType() {
        contextHolder.remove();
    }
}
