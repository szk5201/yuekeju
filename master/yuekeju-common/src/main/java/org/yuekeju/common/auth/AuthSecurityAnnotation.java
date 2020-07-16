package org.yuekeju.common.auth;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthSecurityAnnotation {
	/**
	 * 是否验证  true验证  false  不验证
	 * @return
	 */
   public boolean isAuth();
   
   public String perms();
	
}
