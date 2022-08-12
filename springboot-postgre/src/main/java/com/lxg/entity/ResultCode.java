package com.lxg.entity;



/**
 * 响应码枚举，参考HTTP状态码的语义
 */
public enum ResultCode {
	//成功
	SUCCESS(200),
	//失败
	FAIL(400),
	//未认证（签名错误）
	UNAUTHORIZED(401),
	//接口不存在
	NOT_FOUND(404),
	//服务器内部错误
	INTERNAL_SERVER_ERROR(500),

	//空指针异常
	NULL_POINT(-1),
	//类型转换异常
	PARSE_EXCEPTION(-1),
	//数学运算异常
	ARITHMETIC_EXCEPTION(-1),
	ILLEGAL_ACCESS_EXCEPTION(-1);

	private final int code;

	ResultCode(int code) {
		this.code = code;
	}

	public int code() {
		return code;
	}
}
