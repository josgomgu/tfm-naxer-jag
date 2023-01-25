package com.tfm.finalmaster.dao;

import com.tfm.finalmaster.models.Category;
import com.tfm.finalmaster.models.Comparision;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class ComparisionDaoImp implements ComparisionDao{

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @Override
    public List<Comparision> getComparisions() {
        String query = "FROM Comparision";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public boolean deleteComparision(Long comparisionid) {
        Comparision comparision = entityManager.find(Comparision.class,comparisionid);
        if(comparision != null) {
            entityManager.remove(comparision);
            return true;
        }

        return false;
    }

    @Override
    public Comparision getComparision(Long comparisionid) {
        return entityManager.find(Comparision.class,comparisionid);
    }

    @Override
    public Comparision addComparision(Comparision comparision) {
        entityManager.persist(comparision);
        return comparision;
    }

    @Override
    public void updateComparision(Comparision comparision) {
        entityManager.merge(comparision);
    }
}
