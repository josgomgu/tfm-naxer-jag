package com.tfm.finalmaster.dao;

import com.tfm.finalmaster.models.Product;

import java.util.List;

public interface ProductDao {

    List<Product> getProducts();

    boolean deleteProduct(Long productid);

    Product getProduct(Long productid);

    Product addProduct(Product product);

    void updateProduct(Product product);

}