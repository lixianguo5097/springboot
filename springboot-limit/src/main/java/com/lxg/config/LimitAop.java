package com.lxg.config;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author LXG
 * @date 2022-4-11
 */
@Slf4j
@Aspect
@Component
public class LimitAop {
    /**
     * 不同的接口，不同的流量控制
     * map的key为 Limiter.key
     */
    private final Map<String, RateLimiter> limitMap = Maps.newConcurrentMap();

    @Around("@annotation(com.lxg.config.Limit)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        //拿limit的注解
        Limit limit = method.getAnnotation(Limit.class);
        if (limit != null) {
            //key作用：不同的接口，不同的流量控制
            String key=limit.key();
            RateLimiter rateLimiter = null;
            //验证缓存是否有命中key
            if (!limitMap.containsKey(key)) {
                // 创建令牌桶
                rateLimiter = RateLimiter.create(limit.permitsPerSecond());
                limitMap.put(key, rateLimiter);
                log.info("新建了令牌桶={}，容量={}",key,limit.permitsPerSecond());
            }
            rateLimiter = limitMap.get(key);
            /**
             * 由于RateLimiter是属于单位时间内生成多少个令牌的方式，
             * 譬如0.1秒生成1个，那抢购就要看运气了，
             * 你刚好是在刚生成1个时进来了，那么你就能抢到，
             * 在这0.1秒内其他的请求就算白瞎了，
             * 只能寄希望于下一个0.1秒，而从用户体验上来说，不能让他在那一直阻塞等待，
             * 所以就需要迅速判断，该用户在某段时间内，还有没有机会得到令牌，
             * 这里就需要使用tryAcquire(long timeout, TimeUnit unit)方法，
             * 指定一个超时时间，一旦判断出在timeout时间内还无法取得令牌，就返回false。
             * 注意，这里并不是真正的等待了timeout时间，
             * 而是被判断为即便过了timeout时间，也无法取得令牌。这个是不需要等待的。
             */
            // 拿令牌
            boolean acquire = rateLimiter.tryAcquire(limit.timeout(), limit.timeunit());
            // 拿不到命令，直接返回异常提示
            if (!acquire) {
                log.info("令牌桶={}，获取令牌失败",key);
                return limit.msg();
            }
        }
        return joinPoint.proceed();
    }

}
