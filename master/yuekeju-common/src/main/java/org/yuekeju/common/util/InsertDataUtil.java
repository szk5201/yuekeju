package org.yuekeju.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yuekeju.common.entity.user.YuekejuDeptEntity;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * @author szk
 * @description
 * @date 2020/9/5
 */
@Component
public class InsertDataUtil {

    private static SnowflakeId snowflakeId;

    @Autowired
    public void setSnowflakeId(SnowflakeId snowflakeId) {
        InsertDataUtil.snowflakeId = snowflakeId;
    }

    public static <T> void createData(T data) {
        Class clazz = data.getClass();
        try {
            for (; clazz != Object.class; clazz = clazz.getSuperclass()) { // 向上循环 遍历父类
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    String name = field.getName();
                    if (name.equals("yuekejuCode")) {
                        field.setAccessible(true);
                        field.set(data, snowflakeId.nextIdString());
                    } else if (name.equals("creater")) {

                    } else if (name.equals("modified")) {

                    } else if (name.equals("createTime")) {
                        field.setAccessible(true);
                        field.set(data, new Date());
                    } else if (name.equals("updateTime")) {
                        field.setAccessible(true);
                        field.set(data, new Date());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <T> void updateData(T data) {
        try {
            Class clazz = data.getClass();
            for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
                Field[] declaredFields = clazz.getDeclaredFields();
                for (Field field : declaredFields) {
                    String name = field.getName();
                    if (name.equals("modified")) {

                    } else if (name.equals("updateTime")) {
                        field.setAccessible(true);
                        field.set(data, new Date());

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IllegalAccessException {
        YuekejuDeptEntity yuekejuDeptEntity = new YuekejuDeptEntity();
        yuekejuDeptEntity.setDeptCode("1001");
        createData(yuekejuDeptEntity);
        System.out.println(yuekejuDeptEntity.toString());
    }
}
