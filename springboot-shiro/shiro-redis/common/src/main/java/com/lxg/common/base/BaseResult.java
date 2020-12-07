package com.lxg.common.base;

import lombok.Data;

import java.util.HashMap;

/**
 * @ClassName BaseResult
 * @Author ph
 * @Version 1.0
 * @Description
 * @Date 2020/4/7 17:06
 */
@Data
public class BaseResult {


	/**
	 * 成功的code
	 */
	public static final Integer SUCCESS_CODE = 200;


	private Object data;

	private String msg;

	private Integer code ;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public static BaseResult success() {

		return success(null);
	}

	public static BaseResult success(Object object) {
		BaseResult baseResult = new BaseResult();
		baseResult.setCode(200);
		if(object == null){
			object = new HashMap<> (0);
		}
		baseResult.setData(object);
		return baseResult;
	}
	public static BaseResult success(String msg, Object object) {
		BaseResult baseResult = new BaseResult();
		baseResult.setCode(200);
		baseResult.setMsg(msg);
		baseResult.setData(object);
		return baseResult;
	}

	public static BaseResult error(Integer code, String msg) {

		BaseResult baseResult = new BaseResult();
		baseResult.setCode(code);
		baseResult.setMsg(msg);
		return baseResult;
	}

	public static BaseResult error(String msg) {
		BaseResult baseResult = new BaseResult();
		baseResult.setCode(-1);
		baseResult.setMsg(msg);
		return baseResult;
	}

}