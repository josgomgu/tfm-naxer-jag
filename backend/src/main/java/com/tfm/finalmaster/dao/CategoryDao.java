package com.tfm.finalmaster.dao;

import com.tfm.finalmaster.models.Category;

import java.util.List;

public interface CategoryDao {

    List<Category> getCategories();

    boolean deleteCategory(Long categoryid);

    Category getCategory(Long productid);

    Category addCategory(Category category);

    void updateCategory(Category category);

}