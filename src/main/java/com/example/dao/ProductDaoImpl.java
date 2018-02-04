package com.example.dao;

import com.example.model.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Collection;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    @Override
    public void addProduct(Product p) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(p);
        transaction.commit();
    }

    @Override
    public Product getProduct(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from com.example.model.Product p where p.id = :id");
        query.setParameter("id", id);
        List<Product> products = query.list();
        transaction.commit();
        return products.get(0);
    }

    @Override
    public Collection<Product> getAllProducts() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from com.example.model.Product");
        List<Product> products = query.list();
        transaction.commit();
        return products;
    }
}
