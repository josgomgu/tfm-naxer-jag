package com.tfm.finalmaster.dao;

import com.tfm.finalmaster.models.ComparisionDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ComparisionDetailDaoImp implements ComparisionDetailDao {

    @PersistenceContext
    EntityManager entityManager;


    @Transactional
    @Override
    public List<ComparisionDetail> getComparisionDetail(String comparisionid) {
        String query = "FROM Comparision_Detail WHERE comparisionid = :comparisionid";
        List<ComparisionDetail> list =  entityManager.createQuery(query)
                .setParameter("comparisionid",comparisionid).getResultList();

        if(list.isEmpty()) {
            return null;
        }

        return list;
    }

    @Override
    public boolean deleteComparisionDetail(Long comparisiondetailid) {
        ComparisionDetail comparisionDetail = entityManager.find(ComparisionDetail.class,comparisiondetailid);
        if(comparisionDetail != null) {
            entityManager.remove(comparisionDetail);
            return true;
        }

        return false;
    }

    @Override
    public ComparisionDetail getComparisionDetail(Long comparisiondetailid) {
        return entityManager.find(ComparisionDetail.class,comparisiondetailid);
    }

    @Override
    public ComparisionDetail addComparisionDetail(ComparisionDetail comparisionDetail) {
        entityManager.persist(comparisionDetail);
        return comparisionDetail;
    }

    @Override
    public void updateComparisionDetail(ComparisionDetail comparisionDetail) {
        entityManager.merge(comparisionDetail);
    }


}
