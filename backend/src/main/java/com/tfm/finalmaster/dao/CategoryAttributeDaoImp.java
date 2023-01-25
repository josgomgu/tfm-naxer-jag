package com.tfm.finalmaster.dao;

import com.tfm.finalmaster.models.CategoryAttribute;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class CategoryAttributeDaoImp implements CategoryAttributeDao{

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @Override
    public List<CategoryAttribute> getCategoryAttributes(Long categoryid) {
        String query = "FROM Category_Attribute WHERE categoryid = :categoryid";
        List<CategoryAttribute> list =  entityManager.createQuery(query)
                .setParameter("categoryid",categoryid).getResultList();

        if(list.isEmpty()) {
            return null;
        }

        return list;
    }

    @Override
    public boolean deleteCategoryAttribute(Long categoryattributeid) {
        CategoryAttribute categoryAttribute = entityManager.find(CategoryAttribute.class,categoryattributeid);
        if(categoryAttribute != null) {
            entityManager.remove(categoryAttribute);
            return true;
        }

        return false;
    }

    @Override
    public CategoryAttribute addCategoryAttribute(CategoryAttribute categoryAttribute) {
        entityManager.persist(categoryAttribute);
        return categoryAttribute;
    }
}
