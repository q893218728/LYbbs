package com.ly.bbs.intercetor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CrosIntercetor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {


        response.setHeader("Access-Control-Allow-Credentials","true");
        //当我们设置上面这个属性为true之后，浏览器就不允许使用ACAO为*了。所以可以使用下面的这种方式起到跟*相同的作用
        //等到项目上线之后，在取消下面这行，改为只允许某一台服务器访问
        response.setHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        //response.setHeader("Access-Control-Allow-Origin","http://localhost:63342");
        return true;
    }
}
