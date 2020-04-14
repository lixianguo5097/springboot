package com.lxg.exception;


import com.alibaba.fastjson.JSON;
import com.lxg.common.Result;
import com.lxg.common.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * 全局异常处理 如果使用@RestControllerAdvice注解
 * 则会将返回的数据类型转换成JSON格式
 * @author LXG
 * @date 2019-11-05
 */
@ControllerAdvice
public class GlobalExceptionHandler extends WebMvcConfigurerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理自定义的业务异常
     * @param e
     */
    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public Result bizExceptionHandler(MyException e,HttpServletRequest request) {
        String message = String.format("接口 [%s] 出现业务异常，异常摘要：%s",
                request.getRequestURI(),
                e.getMessage());
        logger.error(message);
        logger.error("发生业务异常！原因是：{}", e.getMessage());
        return Result.fail(message);
    }

    /**
     * 处理空指针的异常
     * @param e
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public Result exceptionHandler(NullPointerException e,HttpServletRequest request) {
        String message = String.format("接口 [%s] 出现空指针异常，异常摘要：%s",
                request.getRequestURI(),
                e.getMessage());
        logger.error(message);
        logger.error("发生空指针异常！原因是:", e);
        return Result.fail(ResultCode.NULL_POINT,message);
    }

    /**
     * 格式转换异常
     * @param e
     */
    @ExceptionHandler(value = ParseException.class)
    @ResponseBody
    public Result exceptionHandler(ParseException e,HttpServletRequest request) {
        String message = String.format("接口 [%s] 出现格式转换异常，异常摘要：%s",
                request.getRequestURI(),
                e.getMessage());
        logger.error(message);
        logger.error("发生格式转换异常！原因是:", e);
        return Result.fail(ResultCode.PARSE_EXCEPTION,message);
    }

    /**
     * 方法安全权限异常
     * @param e
     */
    @ExceptionHandler(value = IllegalAccessException.class)
    @ResponseBody
    public Result exceptionHandler(IllegalAccessException e,HttpServletRequest request) {
        String message = String.format("接口 [%s] 出现方法安全权限异常，异常摘要：%s",
                request.getRequestURI(),
                e.getMessage());
        logger.error(message);
        logger.error("发生方法安全权限异常！原因是:", e);
        return Result.fail(ResultCode.ILLEGAL_ACCESS_EXCEPTION,message);
    }

    /**
     * 数学运算异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = ArithmeticException.class)
    @ResponseBody
    public Result exceptionHandler(ArithmeticException e,HttpServletRequest request) {
        String message = String.format("接口 [%s] 出现数学运算异常，异常摘要：%s",
                request.getRequestURI(),
                e.getMessage());
        logger.error(message);
        logger.error("发生数学运算异常！原因是:", e);
        return Result.fail(ResultCode.ARITHMETIC_EXCEPTION,"数学运算异常");
    }

    /**
     * 页面未找到异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseBody
    public Result error(NoHandlerFoundException e,HttpServletRequest request) {
        logger.error("发生页面未找到异常:", e);
        return Result.fail(ResultCode.NOT_FOUND,"接口 [" + request.getRequestURI() + "] 不存在");
    }

    /**
     * 处理其他异常
     *
     * @param e
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result exceptionHandler(Exception e, HttpServletRequest request) {
        String message = String.format("接口 [%s] 出现异常，异常摘要：%s",
                request.getRequestURI(),
                e.getMessage());
        logger.error(message);
        logger.error("服务器未知异常！原因是:", e);
        return Result.fail(ResultCode.INTERNAL_SERVER_ERROR, message);
    }
}