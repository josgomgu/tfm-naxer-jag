package com.tfm.finalmaster.dao;

import com.tfm.finalmaster.models.Attribute;
import com.tfm.finalmaster.models.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class AttributeDaoImp implements AttributeDao{

    @PersistenceContext
    EntityManager entityManager;


    @Transactional
    @Override
    public List<Attribute> getAttributes() {
        String query = "FROM Attribute";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public boolean deleteAttribute(Long attributeid) {
        Attribute attribute = entityManager.find(Attribute.class,attributeid);
        if(attribute != null) {
            entityManager.remove(attribute);
            return true;
        }

        return false;
    }

    @Override
    public Attribute getAttribute(Long attributeid) {
        return entityManager.find(Attribute.class,attributeid);
    }

    @Override
    public Attribute addAttribute(Attribute attribute) {
        entityManager.persist(attribute);
        return attribute;
    }

    @Override
    public void updateAttribute(Attribute attribute) {
        entityManager.merge(attribute);
    }
}
