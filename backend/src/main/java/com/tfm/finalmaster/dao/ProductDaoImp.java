package com.tfm.finalmaster.dao;

import com.tfm.finalmaster.models.Product;
import com.tfm.finalmaster.models.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ProductDaoImp implements ProductDao{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public List<Product> getProducts() {
        String query = "FROM Product";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public boolean deleteProduct(Long productid) {
        Product product = entityManager.find(Product.class,productid);
        if(product != null) {
            entityManager.remove(product);
            return true;
        }

        return false;
    }

    @Override
    public Product getProduct(Long productid) {
        return entityManager.find(Product.class,productid);
    }

    @Override
    public Product addProduct(Product product) {
        entityManager.persist(product);
        return product;
    }

    @Override
    public void updateProduct(Product product) {
        entityManager.merge(product);
    }
}
