package com.tfm.finalmaster.dao;

import com.tfm.finalmaster.models.RoleOption;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class RoleOptionDaoImp implements RoleOptionDao{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<RoleOption> getRoleOptions(Long roleid) {
        String query = "FROM Role_Option WHERE roleid = :roleid ";
        List<RoleOption> list =  entityManager.createQuery(query)
                .setParameter("roleid",roleid).getResultList();

        return list;

    }

    @Override
    public boolean deleteRoleOption(Long roleid, Long optionmenuid) {
        String query = "FROM Role_Option WHERE roleid = :roleid AND optionmenuid = :optionmenuid";
        List<RoleOption> list =  entityManager.createQuery(query)
                .setParameter("roleid",roleid)
                .setParameter("optionmenuid",optionmenuid).getResultList();

        if(list.isEmpty()) {
            return false;
        }
        entityManager.remove(list.get(0));
        return true;
    }

    @Override
    public RoleOption addRoleOption(RoleOption roleoption) {
        entityManager.persist(roleoption);
        return roleoption;
    }
}
