package com.lxg.exception;



import com.lxg.config.CustomRealm;
import com.lxg.model.CommonEnum;
import com.lxg.model.Result;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.ParseException;

/**
 * 全局异常处理 如果使用@RestControllerAdvice注解
 * 则会将返回的数据类型转换成JSON格式
 * @author LXG
 * @date 2019-11-05
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * 处理自定义的业务异常
	 * @param e
	 */
	@ExceptionHandler(value = BizException.class)
	@ResponseBody
	public Result bizExceptionHandler(BizException e) {
		logger.error("发生业务异常！原因是：{}", e.getErrorMsg());
		return new Result(e.getErrorCode(), e.getErrorMsg());
	}

	/**
	 * 处理空指针的异常
	 * @param e
	 */
	@ExceptionHandler(value = NullPointerException.class)
	@ResponseBody
	public Result exceptionHandler(NullPointerException e) {
		logger.error("发生空指针异常！原因是:", e);
		return new Result(CommonEnum.BODY_NOT_MATCH,"空指针异常");
	}

	/**
	 * 格式转换异常
	 * @param e
	 */
	@ExceptionHandler(value = ParseException.class)
	@ResponseBody
	public Result exceptionHandler(ParseException e) {
		logger.error("发生格式转换异常！原因是:", e);
		return new Result(CommonEnum.BODY_NOT_MATCH,"格式转换错误");
	}

	/**
	 * 方法安全权限异常
	 * @param e
	 */
	@ExceptionHandler(value = IllegalAccessException.class)
	@ResponseBody
	public Result exceptionHandler(IllegalAccessException e) {
		logger.error("发生方法安全权限异常！原因是反射了private方法:", e);
		return new Result(CommonEnum.INTERNAL_SERVER_ERROR,"方法安全权限异常！不能反射private方法");
	}

	/**
	 * 数学运算异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = ArithmeticException.class)
	@ResponseBody
	public Result exceptionHandler(ArithmeticException e) {
		logger.error("发生数学运算异常:", e);
		return new Result(CommonEnum.INTERNAL_SERVER_ERROR,"数学运算异常");
	}

	/**
	 * 处理其他异常
	 * @param e
	 */
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public Result exceptionHandler(Exception e) {
		logger.error("未知异常！原因是:", e);
		return new Result(CommonEnum.INTERNAL_SERVER_ERROR,"未知异常！");
	}

	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(UnauthorizedException.class)
	@ResponseBody
	public Result handle401(UnauthorizedException e) {
		return new Result(401, e.getMessage());
	}

	// 捕捉shiro的异常
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(value = ShiroException.class)
	@ResponseBody
	public Result handle401(ShiroException e) {
		return new Result(401, e.getMessage());
	}




}
