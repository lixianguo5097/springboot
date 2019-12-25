package com.lxg.exception;

import lombok.Data;

/**
 * 业务异常类
 * @author LXG
 * @date 2019-11-05
 */
@Data
public class BizException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/** 错误码*/
	protected Integer errorCode;
	/** 错误信息*/
	protected String errorMsg;
	public BizException(Integer errorCode, String errorMsg) {
		super(errorCode.toString());
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	@Override
	public String getMessage() {
		return errorMsg;
	}
	@Override
	public Throwable fillInStackTrace() {
		return this;
	}
}
