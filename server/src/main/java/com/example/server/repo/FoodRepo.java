package com.example.server.repo;

import com.example.server.models.Food;
import com.example.server.models.UserFood;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class FoodRepo{

    @PersistenceContext
    protected EntityManager em;

    public FoodRepo() {
        super();
    }

    public List<Food> findAll() {
        TypedQuery<Food> namedQuery =
                em.createQuery("select a from Food a", Food.class);
        return namedQuery.getResultList();
    }

    public List<Food> findById() {
        TypedQuery<Food> namedQuery =
                em.createQuery("select a from Food a", Food.class);
        return namedQuery.getResultList();
    }

    public Food findById(int id) {
        TypedQuery<Food> namedQuery =
                em.createQuery("select a from Food a WHERE a.id = ?1", Food.class);
        return namedQuery.setParameter(1, id).getSingleResult();
    }
}
