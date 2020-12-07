package com.lxg.api.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lxg.common.exception.MyException;
import com.lxg.user.mapper.SysLogMapper;
import com.lxg.user.model.SysLog;
import com.lxg.user.model.SysUser;
import com.lxg.user.utils.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
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
 * @Author lxg
 * @Date 2018-10-26 0026
 */
@Slf4j
@Aspect
@Component
public class SysLogAspect {

    private static String CODE="code";
    @Resource
    private SysLogMapper sysLogMapper;


    @Pointcut("execution(public * com.lxg.api.controller..*.*(..))")
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
                SysLog sysLog = new SysLog();
                sysLog.setOperation(logDTO.getOperation());
                sysLog.setOperationModule(logDTO.getOperateModule());
                SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
                if (user == null) {
                    //获取请求的参数
                    Object[] args = joinPoint.getArgs();
                    String params = null;
                    if (args != null && args.length != 0) {
                        params = JSON.toJSONString(args[0]);
                    }
                    try {
                        sysLog.setUsername(JSON.parseObject(params).getString("username"));
                    } catch (NullPointerException e) {
                        throw new MyException(401, "Unauthorized");
                    }
                } else {
                    sysLog.setUsername(ShiroUtils.getUserName());
                }


                //设置请求地址url
                sysLog.setUrl(requestURI);
                sysLog.setDateTime(new Date());
                //如果返回错误，记录错误返回参数
                if (ret != null) {
                    String respJson = JSON.toJSONString(ret);
                    JSONObject object = JSON.parseObject(respJson);
                    if (object.getInteger(CODE) != 200) {
                        sysLog.setRespParam(respJson);
                        sysLog.setErrorLogFlag(true);
                    }
                }
                sysLogMapper.insert(sysLog);
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
