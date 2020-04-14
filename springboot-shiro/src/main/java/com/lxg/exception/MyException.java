package com.lxg.exception;

import lombok.Data;

/**
 * 业务异常类
 * @author LXG
 * @date 2019-11-05
 */
@Data
public class MyException extends RuntimeException {
	private Integer code;

	public MyException(Integer code, String msg) {
		super(msg);
		this.code = code;
	}

	public static MyException newException(Integer code, String msg) {
		return new MyException(code, msg);
	}

	public static MyException newException(String msg) {
		return new MyException(-1, msg);
	}

	public static MyException newSpecialCodeException(Integer code){
		return new MyException(code,null);
	}

	public static MyException newSpecialCodeException(Integer code,String msg){
		return new MyException(code,msg);
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
}
