package ru.work.todo.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest)servletRequest;
//        HttpServletResponse res = (HttpServletResponse)servletResponse;
//        String uri = req.getRequestURI();
//        if (uri.endsWith("loginPage")|| uri.endsWith("login")||uri.endsWith("formRegistration")){
////        if (uri.endsWith("loginPage")|| uri.endsWith("login")||uri.endsWith("formRegistration")||uri.endsWith("userRegistration")){
////        if (uri.endsWith("login")||uri.endsWith("registration")){
//            filterChain.doFilter(req,res);
//            return;
//        }
//        if (req.getSession().getAttribute("user")==null){
//            res.sendRedirect(req.getContextPath()+"/login");
//            return;
//        }
//        filterChain.doFilter(req,res);
    }
}
