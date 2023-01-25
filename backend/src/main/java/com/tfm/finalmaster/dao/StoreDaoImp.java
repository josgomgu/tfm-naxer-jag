package com.tfm.finalmaster.dao;

import com.tfm.finalmaster.models.Category;
import com.tfm.finalmaster.models.Store;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class StoreDaoImp implements StoreDao{

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @Override
    public List<Store> getStores() {
        String query = "FROM Store";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public boolean deleteStore(Long storeid) {
        Store store = entityManager.find(Store.class,storeid);
        if(store != null) {
            entityManager.remove(store);
            return true;
        }

        return false;
    }

    @Override
    public Store getStore(Long storeid) {
        return entityManager.find(Store.class,storeid);
    }

    @Override
    public Store addStore(Store store) {
        entityManager.persist(store);
        return store;
    }

    @Override
    public void updateStore(Store store) {
        entityManager.merge(store);
    }
}
