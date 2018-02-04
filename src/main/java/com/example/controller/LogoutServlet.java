package com.example.controller;

import com.example.Constants;
import com.example.CssSettings;
import com.example.model.LoginView;
import com.example.util.RequestHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LogoutServlet", urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if (session != null) {
            session.invalidate();

            CssSettings css = RequestHelper.unsetLoginError();
            req.setAttribute(Constants.CSS_SETTINGS, css);

            LoginView vm = RequestHelper.getLoginView(req);
            req.setAttribute(Constants.USER_MODEL, vm);

            resp.sendRedirect(req.getContextPath() + "/product");
        }
    }
}
