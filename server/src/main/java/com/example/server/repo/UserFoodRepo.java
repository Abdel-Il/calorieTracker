package com.example.server.repo;

import com.example.server.models.Food;
import com.example.server.models.UserFood;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UserFoodRepo {

    @PersistenceContext
    protected EntityManager em;

    public UserFood save(UserFood userFood) {
        return em.merge(userFood);
    }

    public List<UserFood> findUserFoods(int id) {
        TypedQuery<UserFood> namedQuery =
                em.createQuery("select a from UserFood a WHERE a.user.id = ?1", UserFood.class);
        return namedQuery.setParameter(1, id).getResultList();
    }
}
