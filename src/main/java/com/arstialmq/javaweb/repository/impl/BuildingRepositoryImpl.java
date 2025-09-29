package com.arstialmq.javaweb.repository.impl;

import com.arstialmq.javaweb.builder.BuildingSearchBuilder;
import com.arstialmq.javaweb.repository.BuildingRepository;
import com.arstialmq.javaweb.repository.entity.BuildingEntity;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

@Repository
@Primary
public class BuildingRepositoryImpl implements BuildingRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<BuildingEntity> findAll(BuildingSearchBuilder buildingBuilder) {

        //JPQL
//        String sql = "FROM BuildingEntity b ";
//        Query q = entityManager.createQuery(sql, BuildingEntity.class);

        //SQL Native
        String sql = "SELECT * FROM building b WHERE b.name like '%building%'";
        Query q = entityManager.createNativeQuery(sql, BuildingEntity.class);
        return q.getResultList();
    }
}
