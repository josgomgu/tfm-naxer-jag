package com.tfm.finalmaster.dao;

import com.tfm.finalmaster.models.Specification;

import java.util.List;

public interface SpecificationDao {

    List<Specification> getSpecifications(String attributeid);

    boolean deleteSpecification(Long specificationid);

    Specification getSpecification(Long specificationid);

    Specification addSpecification(Specification specification);

    void updateSpecification(Specification specification);

}