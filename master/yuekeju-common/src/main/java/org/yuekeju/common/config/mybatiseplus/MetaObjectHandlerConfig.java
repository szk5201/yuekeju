package org.yuekeju.common.config.mybatiseplus;

import java.util.Date;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.yuekeju.common.util.SnowflakeId;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
@Configuration
public class MetaObjectHandlerConfig extends MetaObjectHandler{
	@Autowired
	private SnowflakeId snowFlakeId; 
	@Override
	public void insertFill(MetaObject metaObject) {
		// 获取到需要被填充的字段值
        Object yuekejuCode = getFieldValByName("yuekejuCode", metaObject);
        Object createTime = getFieldValByName("createTime", metaObject);
        Object updateTime = getFieldValByName("updateTime", metaObject);
        if(yuekejuCode == null){
            setFieldValByName("yuekejuCode",snowFlakeId.nextIdString(),metaObject);
        }
        if(createTime==null){
        	setFieldValByName("createTime", new Date(),metaObject);
        }
        if(updateTime==null){
        	setFieldValByName("updateTime", new Date(),metaObject);
        }
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		Object updateTime = getFieldValByName("updateTime", metaObject);
		if(updateTime==null){
        	setFieldValByName("updateTime", new Date(),metaObject);
        }
	}

}
