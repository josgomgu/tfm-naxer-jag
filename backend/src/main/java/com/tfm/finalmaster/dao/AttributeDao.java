package com.tfm.finalmaster.dao;

import com.tfm.finalmaster.models.Attribute;

import java.util.List;

public interface AttributeDao {

    List<Attribute> getAttributes();

    boolean deleteAttribute(Long attributeid);

    Attribute getAttribute(Long attributeid);

    Attribute addAttribute(Attribute attribute);

    void updateAttribute(Attribute attribute);

}