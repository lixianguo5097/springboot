package com.lxg.common.enums;


import com.lxg.exception.BaseErrorInfoInterface;

/**
 * @Description: 公用描述枚举类
 * @Author: XIANGUO LI
 * @Date: 2019-11-05
 */
public enum CommonEnum implements BaseErrorInfoInterface {
	// 数据操作错误定义
	SUCCESS(true,200, "请求成功!"),
	ERROR(false,201, "请求失败!"),
	BODY_NOT_MATCH(false,400,"请求的参数错误!"),
	SIGNATURE_NOT_MATCH(false,401,"请求的数字签名不匹配!"),
	NOT_FOUND(false,404, "未找到该资源!"),
	INTERNAL_SERVER_ERROR(false,500, "服务器内部错误!"),
	SERVER_BUSY(false,503,"服务器正忙，请稍后再试!");


	/** 错误码 */
	private Integer resultCode;

	/** 错误描述 */
	private String resultMsg;

	private Boolean flag;

	CommonEnum(Boolean flag, Integer resultCode, String resultMsg) {
		this.resultCode = resultCode;
		this.resultMsg = resultMsg;
		this.flag = flag;
	}

	@Override
	public Boolean flag() {
		return flag;
	}

	@Override
	public Integer getResultCode() {
		return resultCode;
	}

	@Override
	public String getResultMsg() {
		return resultMsg;
	}

}
