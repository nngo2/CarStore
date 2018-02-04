package com.example.controller;

import com.example.Constants;
import com.example.dao.StateDb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "CheckoutServlet", urlPatterns = "/checkout")
public class CheckoutServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        synchronized (this) {
            StateDb db = (StateDb)this.getServletContext().getAttribute(Constants.STATE_DB);
            if (db == null) {
                db = new StateDb();
                this.getServletContext().setAttribute(Constants.STATE_DB, db);
            }
        }
    }

    @Override
    public void destroy() {
        this.getServletContext().removeAttribute(Constants.STATE_DB);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StateDb db = (StateDb)this.getServletContext().getAttribute(Constants.STATE_DB);
        req.setAttribute(Constants.STATE_SESSION, db.getStates());
        req.getRequestDispatcher("/WEB-INF/jsp/checkout.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        req.setAttribute(Constants.CONFIRMED_CARD, session.getAttribute(Constants.CARD_SESSION));
        session.removeAttribute(Constants.CARD_SESSION);
        req.getRequestDispatcher("/WEB-INF/jsp/thank.jsp").forward(req,resp);
    }
}
