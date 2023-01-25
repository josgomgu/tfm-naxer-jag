package com.tfm.finalmaster.dao;

import com.tfm.finalmaster.models.RecordProduct;
import com.tfm.finalmaster.models.RecordProductDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class RecordProductDetailDaoImp implements RecordProductDetailDao {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @Override
    public List<RecordProductDetail> getRecordProductsDetail(Long recordproductid) {
        String query = "FROM Record_Product_Detail WHERE recordproductid = :recordproductid";
        List<RecordProductDetail> list =  entityManager.createQuery(query)
                .setParameter("recordproductid",recordproductid).getResultList();

        if(list.isEmpty()) {
            return null;
        }

        return list;
    }

    @Override
    public boolean deleteRecordProductDetail(Long recordproductdetailid) {
        RecordProductDetail recordProductDetail = entityManager.find(RecordProductDetail.class,recordproductdetailid);
        if(recordProductDetail != null) {
            entityManager.remove(recordProductDetail);
            return true;
        }

        return false;
    }

    @Override
    public RecordProductDetail addRecordProductDetail(RecordProductDetail recordProductDetail) {
        entityManager.persist(recordProductDetail);
        return recordProductDetail;
    }
}
