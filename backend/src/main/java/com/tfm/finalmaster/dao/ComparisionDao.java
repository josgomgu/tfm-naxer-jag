package com.tfm.finalmaster.dao;

import com.tfm.finalmaster.models.Comparision;

import java.util.List;

public interface ComparisionDao {

    List<Comparision> getComparisions();

    boolean deleteComparision(Long comparisionid);

    Comparision getComparision(Long comparisionid);

    Comparision addComparision(Comparision comparision);

    void updateComparision(Comparision comparision);

}