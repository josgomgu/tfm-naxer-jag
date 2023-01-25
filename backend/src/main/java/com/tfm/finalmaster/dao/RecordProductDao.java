package com.tfm.finalmaster.dao;

import com.tfm.finalmaster.models.RecordProduct;

import java.util.List;

public interface RecordProductDao {

    List<RecordProduct> getRecordsProduct(Long productid, Long userid);

    boolean deleteRecordProduct(Long recordproductid);

    RecordProduct addRecordProduct(RecordProduct recordProduct);

}