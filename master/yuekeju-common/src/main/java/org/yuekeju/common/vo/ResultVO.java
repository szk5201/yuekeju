package org.yuekeju.common.vo;

import lombok.Data;

/**
 * 2020-7-10
 * @author szk
 *返回值公共类
 */
@Data
public class ResultVO<T> {

	private  Integer returnCode;
	private Boolean isSuccess;
	private String message;
	private T returnDate;
	public void success(ResultVO result){
		result.isSuccess=true;
		result.returnCode=200;
	}
	public void error(ResultVO result){
		result.isSuccess=false;
		result.returnCode=500;
	}
	public ResultVO(Integer returnCode, Boolean isSuccess, String message, T returnDate) {
		super();
		this.returnCode = returnCode;
		this.isSuccess = isSuccess;
		this.message = message;
		this.returnDate = returnDate;
	}

	public ResultVO() {
		super();
	}
	
}
