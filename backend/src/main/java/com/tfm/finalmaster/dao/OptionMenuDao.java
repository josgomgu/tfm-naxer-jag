package com.tfm.finalmaster.dao;

import com.tfm.finalmaster.models.OptionMenu;

import java.util.List;

public interface OptionMenuDao {

    List<OptionMenu> getOptionsMenu();

    boolean deleteOptionMenu(Long optionmenuid);

    OptionMenu getOptionMenu(Long optionmenuid);

    OptionMenu addOptionMenu(OptionMenu optionMenu);

    void updateOptionMenu(OptionMenu optionMenu);
}