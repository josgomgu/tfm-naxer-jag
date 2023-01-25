package com.tfm.finalmaster.dao;

import com.tfm.finalmaster.models.RecordProductDetail;

import java.util.List;

public interface RecordProductDetailDao {

    List<RecordProductDetail> getRecordProductsDetail(Long recordproductid);

    boolean deleteRecordProductDetail(Long recordproductdetailid);

    RecordProductDetail addRecordProductDetail(RecordProductDetail recordProductDetail);

}