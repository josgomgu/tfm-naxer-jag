package com.tfm.finalmaster.dao;

import com.tfm.finalmaster.models.OptionMenu;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class OptionMenuImp implements OptionMenuDao{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public List<OptionMenu> getOptionsMenu() {
        String query = "FROM Option_Menu";
        return entityManager.createQuery(query).getResultList();

    }

    @Override
    public boolean deleteOptionMenu(Long optionmenuid) {
        OptionMenu optionMenu = entityManager.find(OptionMenu.class,optionmenuid);
        if(optionMenu != null) {
            entityManager.remove(optionMenu);
            return true;
        }

        return false;
    }

    @Override
    public OptionMenu getOptionMenu(Long optionmenuid) {
        return entityManager.find(OptionMenu.class,optionmenuid);
    }


    @Override
    public OptionMenu addOptionMenu(OptionMenu optionMenu) {
        entityManager.persist(optionMenu);
        return optionMenu;
    }

    @Override
    public void updateOptionMenu(OptionMenu optionMenu) {
        entityManager.merge(optionMenu);
    }

}
