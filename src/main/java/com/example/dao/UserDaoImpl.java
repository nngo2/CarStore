package com.example.dao;

import com.example.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public void addUser(User u) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(u);
        transaction.commit();
    }

    @Override
    public boolean isUserExisted(User u) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from com.example.model.User u where u.username = :username and u.password = :password");
        query.setParameter("username", u.getUsername());
        query.setParameter("password", u.getPassword());
        List<User> userList = query.list();
        transaction.commit();
        if (userList.size() > 0) {
            u.setId(userList.get(0).getId());
            u.setFirstName(userList.get(0).getFirstName());
            u.setLastName(userList.get(0).getLastName());
            u.setUserRole(userList.get(0).getUserRole());
        }
        return userList.size() > 0;
    }

    @Override
    public boolean isUserNameExisted(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from com.example.model.User u where u.username = :username");
        query.setParameter("username", name);
        List<User> userList = query.list();
        transaction.commit();
        return userList.size() > 0;
    }
}
