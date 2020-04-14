package com.lxg.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lxg.exception.MyException;
import com.lxg.mapper.LogMapper;
import com.lxg.config.ResultCode;
import com.lxg.model.Log;
import com.lxg.util.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @Author LiuLP
 * @Date 2018-10-26 0026
 */
@Slf4j
@Aspect
@Component
public class SysLogAspect {

    private static String CODE="code";
    @Resource
    private LogMapper logMapper;


    @Pointcut("execution(public * com.lxg.controller..*.*(..))")
    public void sysLog() {
    }

    @Before("sysLog()")
    public void doBefore(JoinPoint joinPoint) throws Exception {
        try {
            // 接收到请求，记录请求内容
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();

            String requestURI = request.getRequestURI();

            // 记录下请求内容
            log.info("URL : " + requestURI);
            log.info("请求参数 : " + JSON.toJSONString(joinPoint.getArgs()));

        } catch (Exception e) {
            if (e instanceof MyException) {
                throw e;
            } else {
                log.info("记录操作日志异常：" + e.getMessage());
            }
        }
    }


    @AfterReturning(returning = "ret", pointcut = "sysLog()")
    public void doAfterReturning(JoinPoint joinPoint, Object ret) {

        try {
            // ServletRequestAttributes 要在项目启动后才能获取到，不然会为null
            //所以要放到try,catch中，不然会抛出空指针异常
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            String requestURI = request.getRequestURI();
            //获取方法日志注解
            SysLogDTO logDTO = getControllerMethodDescription(joinPoint);

            if (logDTO != null) {
                //获取请求的参数
                Log log = new Log();
                log.setOperation(logDTO.getOperation());
                log.setOperationModule(logDTO.getOperateModule());
                String user = (String) SecurityUtils.getSubject().getPrincipal();

                if (user == null) {
                    //获取请求的参数
                    Object[] args = joinPoint.getArgs();
                    String params = null;
                    if (args != null && args.length != 0) {
                        params = JSON.toJSONString(args[0]);
                    }
                    try {
                        log.setUsername(JSON.parseObject(params).getString("username"));
                    } catch (NullPointerException e) {
                        throw new MyException(401, "Unauthorized");
                    }
                } else {
                    log.setUsername(JWTUtil.getUsername(user));
                }


                //设置请求地址url
                log.setUrl(requestURI);
                log.setDateTime(new Date());
                //如果返回错误，记录错误返回参数
                if (ret != null) {
                    String respJson = JSON.toJSONString(ret);
                    System.out.println(respJson);
                    JSONObject object = JSON.parseObject(respJson);
                    if (object.getInteger(CODE) != ResultCode.SUCCESS.code()) {
                        log.setRespParam(respJson);
                        log.setErrorLogFlag(true);
                    }
                }
                logMapper.insert(log);
            }
        } catch (Exception e) {
            log.info("记录操作日志异常：" + e.getMessage());
            e.printStackTrace();
        }
    }


    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     * @author chenyi
     */
    public static SysLogDTO getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();

        SysLogDTO dto = new SysLogDTO();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    Annotation annotation = method.getAnnotation(SysLogAop.class);
                    if (annotation != null) {
                        dto.setOperateModule(((SysLogAop) annotation).operateModule());
                        dto.setOperation(((SysLogAop) annotation).operation());
                        return dto;
                    }
                }
            }
        }
        return null;
    }
}
