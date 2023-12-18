package com.workintech.s18d1.repository;

import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;
import com.workintech.s18d1.exceptions.BurgerException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public class BurgerDaoImpl implements BurgerDao{
    private EntityManager entityManager;

    public BurgerDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public Burger save(Burger burger) {
        entityManager.persist(burger);
        return burger;
    }

    @Override
    public List<Burger> findAll() {
        TypedQuery<Burger> foundAll= entityManager.createQuery("SELECT b FROM Burger b", Burger.class);
        return foundAll.getResultList();
    }

    @Override
    public Burger findById(long id) {
        Burger burger =entityManager.find(Burger.class,id);

        return burger;
    }

    @Transactional
    @Override
    public Burger update(Burger burger) {
        return entityManager.merge(burger);
    }

    @Transactional
    @Override
    public Burger remove(long id) {
       Burger foundBurger = findById(id);
       entityManager.remove(foundBurger);
       return foundBurger;
    }

    @Override
    public List<Burger> findByPrice(Integer price) {
        TypedQuery<Burger> foundListQuery=entityManager.createQuery("SELECT b FROM Burger b WHERE b.price>:price", Burger.class);
        foundListQuery.setParameter("price",price);
        return foundListQuery.getResultList();
    }

    @Override
    public List<Burger> findByBreadType(BreadType breadType) {
        TypedQuery<Burger> foundListQuery=entityManager.createQuery("SELECT b FROM Burger b WHERE b.breadType=:breadType ORDER BY b.name desc", Burger.class);
        foundListQuery.setParameter("breadType",breadType);
        return foundListQuery.getResultList();
    }

    @Override
    public List<Burger> findByContent(String content) {
        TypedQuery<Burger> foundListQuery=entityManager.createQuery("SELECT b FROM Burger b WHERE b.content LIKE CONCAT('%',:content,'%') ORDER BY b.name", Burger.class);
        foundListQuery.setParameter("content",content);
        return foundListQuery.getResultList();
    }

}
