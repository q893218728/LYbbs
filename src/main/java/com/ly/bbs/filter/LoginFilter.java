package com.ly.bbs.filter;

import com.ly.bbs.entity.GithubUser;
import com.ly.bbs.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        request.setCharacterEncoding("utf-8");

        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Access-Control-Allow-Credentials","true");
        //当我们设置上面这个属性为true之后，浏览器就不允许使用ACAO为*了。所以可以使用下面的这种方式起到跟*相同的作用
        //等到项目上线之后，在取消下面这行，改为只允许某一台服务器访问
        response.setHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        HttpSession session = request.getSession();
        String uri = request.getRequestURI();
        if(uri.endsWith(".css") || uri.endsWith(".js")|| uri.endsWith("callback") || uri.endsWith("ajax")||uri.endsWith("getQuestion")||uri.endsWith("question")
        ||uri.endsWith("commentList")||uri.endsWith("login")||uri.endsWith("register")){
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            User user = (User) session.getAttribute("user");
            if(user == null){
                   //Todo 滚回登录页面
                response.sendRedirect("http://localhost:9999/loginAlert");
            }
            if(user != null){
                filterChain.doFilter(servletRequest,servletResponse);
            }
        }

    }

    @Override
    public void destroy() {

    }
}
