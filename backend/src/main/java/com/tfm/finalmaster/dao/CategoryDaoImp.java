package com.tfm.finalmaster.dao;

import com.tfm.finalmaster.models.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CategoryDaoImp implements CategoryDao{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public List<Category> getCategories() {
        String query = "FROM Category";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public boolean deleteCategory(Long categoryid) {
        Category category = entityManager.find(Category.class,categoryid);
        if(category != null) {
            entityManager.remove(category);
            return true;
        }

        return false;
    }

    @Override
    public Category getCategory(Long categoryid) {
        return entityManager.find(Category.class,categoryid);
    }

    @Override
    public Category addCategory(Category category) {
        entityManager.persist(category);
        return category;
    }

    @Override
    public void updateCategory(Category category) {
        entityManager.merge(category);
    }
}
