package com.tfm.finalmaster.dao;

import com.tfm.finalmaster.models.RoleOption;
import com.tfm.finalmaster.models.UserRole;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UserRoleDaoImp implements UserRoleDao{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<UserRole> getUserRoles(Long userid) {
        String query = "FROM User_Role WHERE userid = :userid ";
        List<UserRole> list =  entityManager.createQuery(query)
                .setParameter("userid",userid).getResultList();

        return list;
    }

    @Override
    public boolean deleteUserRole(Long userid, Long roleid) {
        String query = "FROM User_Role WHERE userid = :userid AND roleid = :roleid";
        List<RoleOption> list =  entityManager.createQuery(query)
                .setParameter("userid",userid)
                .setParameter("roleid",roleid).getResultList();

        if(list.isEmpty()) {
            return false;
        }
        entityManager.remove(list.get(0));
        return true;
    }

    @Override
    public UserRole addRoleOption(UserRole userRole) {
        entityManager.persist(userRole);
        return userRole;
    }
}
