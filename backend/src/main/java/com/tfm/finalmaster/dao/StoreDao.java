package com.tfm.finalmaster.dao;

import com.tfm.finalmaster.models.Store;

import java.util.List;

public interface StoreDao {

    List<Store> getStores();

    boolean deleteStore(Long storeid);

    Store getStore(Long storeid);

    Store addStore(Store store);

    void updateStore(Store store);

}