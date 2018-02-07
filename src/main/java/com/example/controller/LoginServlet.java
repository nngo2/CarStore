package com.example.controller;

import com.example.Constants;
import com.example.CssSettings;
import com.example.model.User;
import com.example.dao.UserDao;
import com.example.dao.UserDaoImpl;
import com.example.model.LoginView;
import com.example.util.HashHelper;
import com.example.util.RequestHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private UserDao userDb;

    @Override
    public void init() throws ServletException {
        userDb = new UserDaoImpl();
    }

    @Override
    public void destroy() {
        this.getServletContext().removeAttribute(Constants.USER_DB);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CssSettings css = RequestHelper.unsetLoginError();
        req.setAttribute(Constants.CSS_SETTINGS, css);

        LoginView vm = RequestHelper.getLoginView(req);
        req.setAttribute(Constants.USER_MODEL, vm);

        String lastUrl = req.getHeader("referer");
        HttpSession session = req.getSession();
        session.setAttribute(Constants.LOGIN_REDIRECT_SESSION, lastUrl);

        req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter(Constants.USER_NAME);
        String password = req.getParameter(Constants.USER_PASS);
        String remember = req.getParameter(Constants.REMEMBER_ME);

        User user = new User(username, HashHelper.hash(password));

        if (userDb.isUserExisted(user)) {
            HttpSession session = req.getSession();
            session.setAttribute(Constants.USER_SESSION, user);

            if (remember != null) {
                List<Cookie> cookies = RequestHelper.createRememberMeCookies(username);
                for (Cookie c : cookies) {
                    resp.addCookie(c);
                }
            } else {
                List<Cookie> cookies = RequestHelper.unsetRememberMeCookies();
                for (Cookie c : cookies) {
                    resp.addCookie(c);
                }
            }

            String redirectUrl = (String)session.getAttribute(Constants.LOGIN_REDIRECT_SESSION);
            if (redirectUrl != null)  {
                session.removeAttribute(Constants.LOGIN_REDIRECT_SESSION);
                resp.sendRedirect(redirectUrl);
            } else {
                resp.sendRedirect(req.getContextPath() + "/checkout");
            }
        } else {
            CssSettings css = RequestHelper.setLoginError();
            req.setAttribute(Constants.CSS_SETTINGS, css);

            LoginView vm = RequestHelper.getLoginView(req);
            req.setAttribute(Constants.USER_MODEL, vm);

            req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req,resp);
        }
    }
}
