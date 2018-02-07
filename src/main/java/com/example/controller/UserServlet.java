package com.example.controller;

import com.example.Constants;
import com.example.dao.UserDao;
import com.example.dao.UserDaoImpl;
import com.example.model.PostData;
import com.example.model.User;
import com.example.util.HashHelper;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    private UserDao db;
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public void init() throws ServletException {
        db = new UserDaoImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String jsonString = req.getParameter("command");
        PostData post = mapper.readValue(jsonString, PostData.class);

        if (post.getCommand().equals(Constants.CMD_ADD_USER)) {
            User u = post.getUser();

            if (db.isUserNameExisted(u.getUsername())) {
                out.write(mapper.writeValueAsString(mapper.createObjectNode().put("error", "Login is already used")));
            } else {
                u.setPassword(HashHelper.hash(u.getPassword()));
                db.addUser(u);
                out.write(mapper.writeValueAsString(mapper.createObjectNode().put("success", "User has been created")));
            }
        }
    }
}
