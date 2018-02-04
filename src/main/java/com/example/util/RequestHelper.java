package com.example.util;

import com.example.Constants;
import com.example.CssSettings;
import com.example.model.LoginView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class RequestHelper {
    public static final CssSettings setLoginError() {
        CssSettings css = new CssSettings();
        css.setLoginErrorMessage(Constants.LOGIN_ERROR_MSG);
        css.setLoginErrorMessageClass(Constants.CSS_VISIBLE);
        return css;
    }

    public static final CssSettings unsetLoginError() {
        CssSettings css = new CssSettings();
        css.setLoginErrorMessage("");
        css.setLoginErrorMessageClass(Constants.CSS_INVISIBLE);
        return css;
    }

    public static final List<Cookie> createRememberMeCookies(String username) {
        List<Cookie> cookies = new ArrayList<>();

        Cookie c = new Cookie(Constants.REMEMBER_ME, "checked");
        c.setMaxAge(60*60*24*30);
        cookies.add(c);

        Cookie c2 = new Cookie(Constants.USER_NAME, username);
        c2.setMaxAge(60*60*24*30);
        cookies.add(c2);

        return cookies;
    }

    public static final List<Cookie> unsetRememberMeCookies() {
        List<Cookie> cookies = new ArrayList<>();

        Cookie c = new Cookie(Constants.REMEMBER_ME, "");
        c.setMaxAge(-1);
        cookies.add(c);

        Cookie c2 = new Cookie(Constants.USER_NAME, "");
        c2.setMaxAge(-1);
        cookies.add(c2);

        return cookies;
    }

    public static final LoginView getLoginView(HttpServletRequest req) {
        String rememberme = "";
        String username = "";
        if (req.getCookies() != null) {
            for(Cookie c : req.getCookies()) {
                if (c.getName().equals(Constants.REMEMBER_ME)) {
                    rememberme = c.getValue();
                } else if (c.getName().equals(Constants.USER_NAME)) {
                    username = c.getValue();
                }
            }
        }
        return new LoginView(rememberme, username);
    }
}
