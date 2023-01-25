package com.tfm.finalmaster.dao;

import com.tfm.finalmaster.models.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class RoleDaoImp implements RoleDao{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public List<Role> getRoles() {
        String query = "FROM Role";
        return entityManager.createQuery(query).getResultList();

    }

    @Override
    public boolean deleteRole(Long roleid) {
        Role role = entityManager.find(Role.class,roleid);
        if(role != null) {
            entityManager.remove(role);
            return true;
        }

        return false;
    }

    @Override
    public Role getRole(Long roleid) {
        return entityManager.find(Role.class,roleid);
    }


    @Override
    public Role addRole(Role role) {
        entityManager.persist(role);
        return role;
    }

    @Override
    public void updateRole(Role role) {
        entityManager.merge(role);
    }

}
