package com.lxg.exception;

/**
 * @Description: 基础接口 自定义的错误描述枚举类需实现该接口
 * @Author: XIANGUO LI
 * @Date: 2019-11-05
 */
public interface BaseErrorInfoInterface {
	/**
	 * 返回标识
	 * @return true/false
	 */
	Boolean flag();

	/**
	 *  错误码
	 * @return
	 */
	Integer getResultCode();

	/**
	 * 错误描述
	 * @return
	 */
	String getResultMsg();
}
