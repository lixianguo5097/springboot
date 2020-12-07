package com.lxg.common.intercept;


import com.lxg.common.base.BaseResult;
import com.lxg.common.exception.MyException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName ControllerExceptionHandler
 * @Author ph
 * @Version 1.0
 * @Description 全局异常处理类
 * @Date 2020/3/18 15:32
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class ControllerExceptionHandler {

	@ExceptionHandler(Exception.class)
	public BaseResult processException(Exception e) {
		log.error("服务调用异常", e);
		if (e instanceof MethodArgumentNotValidException) {
			List<ObjectError> errors = ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors();
			if (!CollectionUtils.isEmpty(errors)) {
				String error = errors.get(0).getDefaultMessage();
				return BaseResult.error(error);
			}
			// 返回自定义异常
		} else if (e instanceof MyException) {
			MyException myException = (MyException)e;
			return BaseResult.error(myException.getCode(), myException.getMessage());
		}else if(e instanceof HttpMessageNotReadableException){
			//没有传递请求参数
			return BaseResult.error("缺少请求参数或参数类型不正确");
		}else if(e instanceof HttpMediaTypeNotSupportedException){
			//请求方式错误
			return BaseResult.error(e.getMessage());
		}else if(e instanceof BindException){
			BindException bindException = (BindException)e;
			ObjectError objectError = bindException.getAllErrors().get(0);
			return BaseResult.error(objectError.getDefaultMessage());
		}else if(e instanceof UnauthorizedException){
			return BaseResult.error(-1,"权限不足");
		}
		return BaseResult.error("系统异常");
	}
}
