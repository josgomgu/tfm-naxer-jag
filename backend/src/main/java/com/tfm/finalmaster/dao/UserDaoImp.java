package com.tfm.finalmaster.dao;

import com.tfm.finalmaster.models.User;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UserDaoImp implements UserDao{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public List<User> getUsers() {
       String query = "FROM User";
       return entityManager.createQuery(query).getResultList();

    }

    @Override
    public boolean deleteUser(Long userid) {
        User user = entityManager.find(User.class,userid);
        if(user != null) {
            entityManager.remove(user);
            return true;
        }

        return false;
    }

    @Override
    public User getUser(Long userid) {
        return entityManager.find(User.class,userid);
    }

    @Override
    public User getUserByLogin(String login) {
        String query = "FROM User WHERE login = :login ";
        List<User> list =  entityManager.createQuery(query)
                .setParameter("login",login).getResultList();

        if(list.isEmpty()) {
            return null;
        }

        return list.get(0);
    }

    @Override
    public User addUser(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }


    @Override
    public User getUserCredentials(User user) {

        User u = getUserByLogin(user.getLogin());

        if(u == null)
            return null;

        String passwordHashed = u.getPassword();
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

        if( argon2.verify(passwordHashed,user.getPassword())) {
            return u;
        }

        return null;
    }
}
