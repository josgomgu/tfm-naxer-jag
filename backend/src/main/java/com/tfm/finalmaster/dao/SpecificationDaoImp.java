package com.tfm.finalmaster.dao;

import com.tfm.finalmaster.models.Category;
import com.tfm.finalmaster.models.ComparisionDetail;
import com.tfm.finalmaster.models.Specification;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class SpecificationDaoImp implements SpecificationDao{

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @Override
    public List<Specification> getSpecifications(String attributeid) {
        String query = "FROM Specification WHERE attributeid = :attributeid";
        List<Specification> list =  entityManager.createQuery(query)
                .setParameter("attributeid",attributeid).getResultList();

        if(list.isEmpty()) {
            return null;
        }

        return list;
    }

    @Override
    public boolean deleteSpecification(Long specificationid) {
        Specification specification = entityManager.find(Specification.class,specificationid);
        if(specification != null) {
            entityManager.remove(specification);
            return true;
        }

        return false;
    }

    @Override
    public Specification getSpecification(Long specificationid) {
        return entityManager.find(Specification.class,specificationid);
    }

    @Override
    public Specification addSpecification(Specification specification) {
        entityManager.persist(specification);
        return specification;
    }

    @Override
    public void updateSpecification(Specification specification) {
        entityManager.merge(specification);
    }
}
