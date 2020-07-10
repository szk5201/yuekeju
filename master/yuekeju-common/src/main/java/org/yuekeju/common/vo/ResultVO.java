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
	
	
}
