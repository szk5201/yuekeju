package org.yuekeju.common.vo;

public class ResultVO<T> {

	private  int returnCode;
	private boolean isSuccess;
	private String message;
	private T returnDate;
	public int getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(T returnDate) {
		this.returnDate = returnDate;
	}

	
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public ResultVO() {
		super();
		this.isSuccess = true;
	}
	
	public ResultVO(int returnCode, boolean isSuccess, String message, T returnDate) {
		super();
		this.returnCode = returnCode;
		this.isSuccess = isSuccess;
		this.message = message;
		this.returnDate = returnDate;
	}
	public ResultVO<T> success() {
		this.isSuccess = true;
		this.returnCode = 000000;
		this.message = "请求成功";
		return this;
	}

	public ResultVO<T> error(Integer errCode, String msg) {
		if (errCode == null) {
			this.returnCode = 999999;
		} else {
			this.returnCode = errCode;
		}
		this.message = msg;
		this.isSuccess = false;
		return this;
	}

	
}
