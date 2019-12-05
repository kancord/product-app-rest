package com.productapp.model;

import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

@Repository
@Transactional
public class ProductDao {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public Product getProductByID(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (Product) session.get(Product.class, id);
    }

    public List<Product> getProductList() {
        Session session = sessionFactory.getCurrentSession();
        String sql = "SELECT e FROM " + Product.class.getName() + " e ";
        Query query = session.createQuery(sql);
        return (List<Product>) query.getResultList();
    }

    public boolean hasProduct(int id) {
        Session session = sessionFactory.getCurrentSession();
        String sql = "SELECT count(1) FROM " + Product.class.getName() + " e " //
                + " WHERE e.id = :id";
        Query query = session.createQuery(sql);
        query.setParameter("id", id);
        return (Long) query.uniqueResult() > 0;
    }

    public int getNextIdVal() {
        Session session = sessionFactory.getCurrentSession();
        boolean isExist = true;
        int key = 0;
        while (isExist) {
            Query queryCurrentId = session.createSQLQuery("select nextval('product_pk_seq')");
            key = ((BigInteger) queryCurrentId.uniqueResult()).intValue();
            isExist = hasProduct(key);
        }
        return key;
    }

    public boolean createProduct(String name, int price) {
        Session session = sessionFactory.getCurrentSession();
        Product product = new Product();
        product.setId(getNextIdVal());
        product.setPrice(price);
        product.setProductName(name);
        session.replicate(product, ReplicationMode.IGNORE);
        return true;
    }

    public boolean deleteProduct(int id) {
        Session session = sessionFactory.getCurrentSession();
        Product product = getProductByID(id);
        session.delete(product);
        return true;
    }

    public boolean updateProduct(int id, String name, int price) {
        Session session = sessionFactory.getCurrentSession();
        Product product = getProductByID(id);
        product.setProductName(name);
        product.setPrice(price);
        session.update(product);
        return true;
    }


    public void createProductWithId(int id) {
        Session session = sessionFactory.getCurrentSession();
        Product product = new Product();
        product.setId(id);
        product.setProductName("Product" + id);
        product.setPrice((int) (Math.random() * 4999 + 1));
        session.replicate(product, ReplicationMode.IGNORE);
    }
}
