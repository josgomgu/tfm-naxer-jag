package com.tfm.finalmaster.dao;

import com.tfm.finalmaster.models.CategoryAttribute;

import java.util.List;

public interface CategoryAttributeDao {

    List<CategoryAttribute> getCategoryAttributes(Long categoryid);

    boolean deleteCategoryAttribute(Long categoryattributeid);

    CategoryAttribute addCategoryAttribute(CategoryAttribute categoryAttribute);

}