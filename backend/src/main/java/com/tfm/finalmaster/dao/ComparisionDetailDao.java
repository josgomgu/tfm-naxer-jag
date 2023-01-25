package com.tfm.finalmaster.dao;

import com.tfm.finalmaster.models.ComparisionDetail;

import java.util.List;

public interface ComparisionDetailDao {

    List<ComparisionDetail> getComparisionDetail(String comparisionid);

    boolean deleteComparisionDetail(Long comparisiondetailid);

    ComparisionDetail getComparisionDetail(Long comparisiondetailid);

    ComparisionDetail addComparisionDetail(ComparisionDetail comparisionDetail);

    void updateComparisionDetail(ComparisionDetail comparisionDetail);

}