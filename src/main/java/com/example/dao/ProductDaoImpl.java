package com.example.dao;

import com.example.Constants;
import com.example.model.PagedProduct;
import com.example.model.Paging;
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
    public void deleteProduct(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Product p = new Product();
        p.setId(id);
        session.delete(p);
        transaction.commit();
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

    @Override
    public Paging getPagingInfo() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String countQ = "select count (p.id) from com.example.model.Product p";
        Query countQuery = session.createQuery(countQ);
        Long countResults = (Long) countQuery.uniqueResult();
        int lastPageNumber = (int) (Math.ceil(countResults / Constants.PAGE_SIZE));
        return new Paging(Constants.PAGE_SIZE, 1, lastPageNumber);
    }

    @Override
    public PagedProduct getPagedProducts(Paging page) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String countQ = "select count (p.id) from com.example.model.Product p";
        Query countQuery = session.createQuery(countQ);
        Long countResults = (Long) countQuery.uniqueResult();
        int lastPageNumber = (int) (Math.ceil(countResults / Constants.PAGE_SIZE));
        page.setTotalPage(lastPageNumber);
        page.setTotalCount(countResults);

        Query selectQuery = session.createQuery("from com.example.model.Product");
        selectQuery.setFirstResult((page.getCurrentPage() - 1) * page.getPageSize());
        selectQuery.setMaxResults(page.getPageSize());
        List<Product> products = selectQuery.list();
        transaction.commit();

        PagedProduct pProduct = new PagedProduct();
        pProduct.setPaging(page);
        pProduct.setProducts(products);
        return pProduct;
    }

    @Override
    public void updateProduct(Product p) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Product update = session.get(Product.class, p.getId());
        String imageUrl = update.getImage();
        update = (Product)session.merge(p);
        update.setImage(imageUrl);
        session.persist(update);
        transaction.commit();
    }
}
