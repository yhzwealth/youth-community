package com.chuang.bootplus.base.interceptor;

import com.chuang.bootplus.base.annotation.VisitLimit;
import com.chuang.bootplus.base.exception.BusException;
import com.chuang.bootplus.base.utils.ErrorCode;
import com.chuang.bootplus.base.utils.IPUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author hsy
 * @create 2021-07-19
 * @注意
 */
@Component
public class VisitLimitInterceptor extends HandlerInterceptorAdapter {

    /**
     * 创建缓存，默认1000*60毫秒过期
     */
    private MyTimedCache<String, Integer> timedCache = new MyTimedCache<>(1000 * 60);
    public VisitLimitInterceptor(){
        //启动定时任务，每100毫秒检查一次过期
        timedCache.schedulePrune(100);
    }

    /**
     * 处理请求之前被调用
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            if (!method.isAnnotationPresent(VisitLimit.class)) {
                return true;
            }
            VisitLimit accessLimit = method.getAnnotation(VisitLimit.class);
            if (accessLimit == null) {
                return true;
            }
            int limit = accessLimit.limit();
            long sec = accessLimit.sec();
            String message = accessLimit.message();
            String key = IPUtils.getIpAddr(request) + request.getRequestURI();
            Integer maxLimit =null;
            Integer value = timedCache.get(key, false);
            System.out.println("key："+key+" ,value: "+value);
            if(value!=null) {
                maxLimit = Integer.valueOf(String.valueOf(value));
            }
            if (maxLimit == null) {
                timedCache.put(key, 1, sec);
            } else if (maxLimit < limit) {
                Integer i = maxLimit+1;
                timedCache.putWithoutUpdate(key, i, sec);
            } else {
                throw new BusException(ErrorCode.RE_CODE_OPERATE_FREQUENTLY, message);
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
