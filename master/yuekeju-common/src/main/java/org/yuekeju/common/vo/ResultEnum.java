package org.yuekeju.common.vo;
/**
 * 公共枚举
 * @author szk
 *
 */
public enum ResultEnum {
	
	SYSTEMERROR(500,"系统异常"),
	INSERTSUCCESS(200,"新增成功"),
	INSERTERROR(1001,"新增失败"),
	DELETESUCCESS(200,"删除成功"),
	DELETEERROR(1002,"删除失败"),
	UPDATESUCCESS(200,"修改成功"),
	UPDATEERROR(1003,"修改失败"),
	SELECTSUCCESS(200,"查询成功"),
	SELECTERROR(1003,"查询失败"),
	SELECTBYIDERROR(1004,"传入数据有误，无法进行修改！"),
	UPDATETIMEERROR(1005,"改数据已被修改，请刷新后重试！"),
	FINDNULLERROR(1006,"暂无该数据！"),
	INSERTREPEATEN(1007,"英文名称不能重复!"),
	INSERTREPEATCN(1008,"中文名称不能重复!"),
	PARAMPEATCN(1009,"参数不能为空!");
	
	
	
	
	
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
