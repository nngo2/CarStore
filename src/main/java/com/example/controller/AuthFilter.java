package com.example.controller;

import com.example.Constants;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AuthFilter", urlPatterns = "/checkout")
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false);

        String loginURI = httpRequest.getContextPath() + "/login";
        boolean loginRequest = httpRequest.getRequestURI().equals(loginURI);
        boolean staticFilesRequest = isStaticFiles(httpRequest.getRequestURI());
        boolean alreadyAuthenticated = session != null && session.getAttribute(Constants.USER_SESSION) != null;

        if (loginRequest || staticFilesRequest || alreadyAuthenticated) {
            chain.doFilter(request,response);
        } else {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
        }
    }

    private boolean isStaticFiles(String uri) {
        if (uri.indexOf("/css") > 0) {
            return true;
        } else if (uri.indexOf("/image") > 0) {
            return true;
        }

        return false;
    }
}
