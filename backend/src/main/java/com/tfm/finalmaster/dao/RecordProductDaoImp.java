package com.tfm.finalmaster.dao;

import com.tfm.finalmaster.models.RecordProduct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class RecordProductDaoImp implements RecordProductDao {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @Override
    public List<RecordProduct> getRecordsProduct(Long productid, Long userid) {
        String query = "FROM Record_Product WHERE productid = :productid AND create_user = :userid";
        List<RecordProduct> list =  entityManager.createQuery(query)
                .setParameter("productid",productid)
                .setParameter("userid",userid).getResultList();

        if(list.isEmpty()) {
            return null;
        }

        return list;
    }

    @Override
    public boolean deleteRecordProduct(Long recordproductid) {
        RecordProduct recordProduct = entityManager.find(RecordProduct.class,recordproductid);
        if(recordProduct != null) {
            entityManager.remove(recordProduct);
            return true;
        }

        return false;
    }

    @Override
    public RecordProduct addRecordProduct(RecordProduct recordProduct) {
        entityManager.persist(recordProduct);
        return recordProduct;
    }
}
