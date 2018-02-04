package com.example.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        // This step will read hibernate.cfg.xml and prepare hibernate for use
        Configuration configuration = new Configuration();
        configuration.configure("META-INF/hibernate.cfg.xml");
        sessionFactory = configuration.buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}