package org.yuekeju.common.constants;

/**
 * 2020-7-10
 * @author szk
 * 静态公共类
 */
public class CommonConstants {

	public static final int SUCCESS_CODE = 200;
	public static final int ERROR_CODE = 500;

	public static final String VERSION_CONTROLLER = "/v1";
	public static final String VERSION_CONTROLLER_API = "/v1/api";

	//token 私钥key
	public static final String TOKEN_PRIVATE_KEY = "ceshi";
	//请求类型
	public static final String HTTP_POST = "POST";
	public static final String HTTP_GET = "GET";
	public static final String HTTP_PUT = "PUT";
	public static final String HTTP_DEL = "DELETE";
	//MD5 
	public static final Integer MD5_LENGTH = 16;
	//AES
	public static final Integer AES_EXCEPT = 2;
	public static final String AES = "AES";
	//常亮
	public static final boolean FALSE = false;
	public static final boolean TRUE = true;
	//功能名称
	public static final String BANNER_NAME = "轮播图";
	public static final String DISC_NAME = "词典";
	public static final String ROLE_NAME="角色";
	public static final String PERMISSION_NAME="权限";
	public static final String USER_NAME = "用户";
	//默认分页的当前页和显示行数
	public static final Integer DEFAULT_CURRY_PAGE=1;
	public static final Integer DEFAULT_SIZE = 20;
	//0 代表正常  1代表删除或禁用
	public static final Integer DEFAULT_ENABLE = 0;
	public static final Integer DEFAULT_DEL_TAG = 1;
	//编码集
	public static final String UTF8 = "UTF-8";
	
}
