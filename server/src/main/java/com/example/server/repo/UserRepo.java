package com.example.server.repo;

import com.example.server.models.Food;
import com.example.server.models.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class UserRepo {

    @PersistenceContext
    protected EntityManager em;

    public User findById(int id) {
        TypedQuery<User> namedQuery =
                em.createQuery("select a from User a WHERE a.id = ?1", User.class);
        return namedQuery.setParameter(1, id).getSingleResult();
    }
}
