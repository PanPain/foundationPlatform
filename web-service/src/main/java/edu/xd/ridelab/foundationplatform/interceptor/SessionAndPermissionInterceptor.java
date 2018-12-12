package edu.xd.ridelab.foundationplatform.interceptor;

import edu.xd.ridelab.foundationplatform.service.impl.InterceptorPermissionCheckImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Service
public class SessionAndPermissionInterceptor implements HandlerInterceptor {

    @Autowired
    InterceptorPermissionCheckImpl interceptorPermissionCheck;


    /**
     *该方法将在请求处理之前进行调用，只有该方法返回true，才会继续执行后续的Interceptor和Controller，
     * 当返回值为true 时就会继续调用下一个Interceptor的preHandle 方法，
     * 如果已经是最后一个Interceptor的时候就会是调用当前请求的Controller方法；
     *
     * 主要用于登录检查和鉴权模块
`    */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        HttpSession  httpSession = httpServletRequest.getSession();
//        Object uid = httpSession.getAttribute("userId");
        Object uid = "2";
        if(uid == null){
            System.out.println("Session Uid Is Null, Redirect to Login");
            httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN  , "Please Singin"); //如果用户未登录，重定向的登录界面
            return false;
        }
        String requestUri = httpServletRequest.getRequestURI();
        System.out.println("请求的URL ： " + requestUri);
        long userId = Long.valueOf(uid.toString());
        boolean permissionCheck = interceptorPermissionCheck.checkUserHasPermission(userId , requestUri); //权限检查
        if(!permissionCheck){
            //如果无权限
            System.out.println("User "+userId+" Request for "+requestUri+" has no permission, redirect");
            httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN , "Access Denied");
            return false;
        }
//        HttpSession  httpSession = httpServletRequest.getSession();
//        Object uid = httpSession.getAttribute("userId");
//        uid = 2;
//        if(uid == null){
//            System.out.println("Session Uid Is Null, Redirect to Login");
//            httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN  , "Please Singin"); //如果用户未登录，重定向的登录界面
//            return false;
//        }
//        String requestUri = httpServletRequest.getRequestURI();
//        System.out.println("请求的URL ： " + requestUri);
//        long userId = Long.valueOf(uid.toString());
//        boolean permissionCheck = interceptorPermissionCheck.checkUserHasPermission(userId , requestUri); //权限检查
//        if(!permissionCheck){
//            //如果无权限
//            System.out.println("User "+userId+" Request for "+requestUri+" has no permission, redirect");
//            httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN , "Access Denied");
//            return false;
//        }
        return true;
    }

    /**
     * 在此处返回系统中哪个页面哪些信息可以显示！！！！
     * */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        //todo
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
