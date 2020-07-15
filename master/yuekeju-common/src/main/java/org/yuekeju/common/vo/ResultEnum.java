package org.yuekeju.common.vo;
/**
 * 公共枚举
 * @author szk
 *
 */
public enum ResultEnum {
	
	SYSTEMERROR(500,"系统异常"),
	INSERTSUCCESS(200,"新增成功"),
	INSERTERROR(200,"新增失败"),
	DELETESUCCESS(200,"删除成功"),
	DELETEERROR(200,"删除失败"),
	UPDATESUCCESS(200,"修改失败"),
	UPDATEERROR(200,"删除失败");
	
	
	
	private  Integer code;
	private String message;
	private ResultEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
