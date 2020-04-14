package com.lxg.exception;


import com.alibaba.fastjson.JSON;
import com.lxg.config.Result;
import com.lxg.config.ResultCode;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 全局异常处理 如果使用@RestControllerAdvice注解
 * 则会将返回的数据类型转换成JSON格式
 *
 * @author LXG
 * @date 2019-11-05
 */
@ControllerAdvice
public class GlobalExceptionHandler extends WebMvcConfigurerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    //全局统一处理异常
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add(new HandlerExceptionResolver() {
            @Override
            public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
                Result result = new Result();
                if (e instanceof MyException) {
                    //业务失败的异常，如“账号或密码错误”
                    //如果需要添加别的异常码，可以在这里添加异常返回结果
                    if (((MyException) e).getCode() == ResultCode.UNAUTHORIZED.code()) {
                        result.setCode(ResultCode.UNAUTHORIZED).setMessage(e.getMessage());
                    } else {
                        result.setCode(ResultCode.FAIL).setMessage(e.getMessage());
                    }
                    logger.info(e.getMessage());
                } else if (e instanceof UnauthorizedException) {
                    result.setCode(ResultCode.UNAUTHORIZED).setMessage(e.getMessage());
                } else if (e instanceof NoHandlerFoundException) {
                    result.setCode(ResultCode.NOT_FOUND).setMessage("接口 [" + request.getRequestURI() + "] 不存在");
                } else if (e instanceof ServletException) {
                    result.setCode(ResultCode.FAIL).setMessage(e.getMessage());
                } else {
                    result.setCode(ResultCode.INTERNAL_SERVER_ERROR).setMessage("接口 [" + request.getRequestURI() + "] 内部错误，请联系管理员");
                    String message;
                    if (handler instanceof HandlerMethod) {
                        HandlerMethod handlerMethod = (HandlerMethod) handler;
                        message = String.format("接口 [%s] 出现异常，方法：%s.%s，异常摘要：%s",
                                request.getRequestURI(),
                                handlerMethod.getBean().getClass().getName(),
                                handlerMethod.getMethod().getName(),
                                e.getMessage());
                    } else {
                        message = e.getMessage();
                    }
                    logger.error(message, e);
                }
                responseResult(response, result);
                return new ModelAndView();
            }

        });
    }

    private void responseResult(HttpServletResponse response, Result result) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(200);
        try {
            response.getWriter().write(JSON.toJSONString(result));
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }

}

