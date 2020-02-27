package com.ly.bbs.filter;

import com.ly.bbs.entity.GithubUser;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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
        /*HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpSession session = request.getSession();
        String uri = request.getRequestURI();
        if(uri.endsWith(".css") || uri.endsWith(".js")|| uri.endsWith("callback")){
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            GithubUser githubUser = (GithubUser) session.getAttribute("githubUser");
            if(githubUser == null){
                   //Todo 滚回登录页面
            }
            if(githubUser != null){
                filterChain.doFilter(servletRequest,servletResponse);
            }
        }*/

    }

    @Override
    public void destroy() {

    }
}
