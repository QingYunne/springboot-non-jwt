package com.arstialmq.javaweb.repository.impl;

import com.arstialmq.javaweb.builder.BuildingSearchBuilder;
import com.arstialmq.javaweb.repository.BuildingRepository;
import com.arstialmq.javaweb.repository.entity.BuildingEntity;
import com.arstialmq.javaweb.utils.NumberUtil;
import com.arstialmq.javaweb.utils.StringUtil;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.*;

@Repository
@Primary
public class JPABuildingRepositoryImpl implements BuildingRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private static void joinTable(BuildingSearchBuilder builder, StringBuilder sql) {
        Optional.ofNullable(builder.getStaffId()).ifPresent(staffId -> sql.append(" INNER JOIN b.assignmentBuildings ab"));
        List<String> typeCode = builder.getTypeCode();
        if (typeCode != null && !typeCode.isEmpty()) {
            sql.append(" INNER JOIN b.rentTypes rt");
        }
        Optional.ofNullable(builder.getRentAreaFrom())
                .flatMap(from -> Optional.ofNullable(builder.getRentAreaTo())
                        .map(to -> new Long[]{from, to}))
                .ifPresent(pair -> {
                    sql.append(" INNER JOIN b.rentAreas ra");
                });
    }

    public static void queryNormal(BuildingSearchBuilder builder, StringBuilder where, Map<String, Object> params) {
        try {
            Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
            for (Field item : fields) {
                item.setAccessible(true);
                String fieldName = item.getName();
                if (!fieldName.equals("staffId") && !fieldName.equals("typeCode") && !fieldName.equals("rentAreaTo") && !fieldName.equals("rentAreaFrom") && !fieldName.startsWith("area") && !fieldName.startsWith("rentPrice") && !fieldName.startsWith("district")) {
                    Object valueObj = item.get(builder);
                    if (valueObj != null && StringUtil.checkString(valueObj.toString())) {
                        String value = valueObj.toString();
                        if (NumberUtil.isNumeric(value)) {
                            where.append(" AND b.").append(fieldName).append(" = :").append(fieldName);
                            params.put(fieldName, value);
                        }
                        else {
                            where.append(" AND b.").append(fieldName).append(" LIKE :").append(fieldName);
                            params.put(fieldName, "%" + value + "%");
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void querySpecial (BuildingSearchBuilder builder, StringBuilder where, Map<String, Object> params) {
        Long staffId = builder.getStaffId();
        if (staffId != null) {
            where.append(" AND ab.staffId = :staffId");
            params.put("staffId", staffId);
        }
        Long rentAreaTo = builder.getRentAreaTo();
        Long rentAreaFrom = builder.getRentAreaFrom();
        if (rentAreaFrom != null || rentAreaTo != null) {
            where.append(" AND EXISTS (SELECT 1 FROM ra WHERE 1 = 1)");
            if (rentAreaTo != null) {
                where.append(" AND ra.value <= :rentAreaTo");
                params.put("rentAreaTo", rentAreaTo);
                if (rentAreaFrom != null) {
                    where.append(" AND ra.value >= :rentAreaFrom");
                    params.put("rentAreaFrom", rentAreaFrom);
                }
                where.append(")");
            }
            Long rentPriceTo = builder.getRentPriceTo();
            Long rentPriceFrom = builder.getRentPriceFrom();
            if (rentPriceFrom != null || rentPriceTo != null) {
                if (rentPriceTo != null) {
                    where.append(" AND b.rentPrice <= :rentPriceTo");
                    params.put("rentPriceTo", rentPriceTo);
                }
                if (rentPriceFrom != null) {
                    where.append(" AND b.rentPrice >= :rentPriceFrom");
                    params.put("rentPriceFrom", rentPriceFrom);
                }
            }

            List<String> typeCode = builder.getTypeCode();
            if (typeCode != null && !typeCode.isEmpty()) {
                where.append(" AND rt.code IN :typeCodes");
                params.put("typeCodes", typeCode);
            }
        }
        String district = builder.getDistrictId();
        if (district != null && NumberUtil.isNumeric(district)) {
            Long districtId = Long.parseLong(builder.getDistrictId());
            where.append(" AND b.district.id = :districtId");
            params.put("districtId", districtId);
        }
    }

    @Override
    public List<BuildingEntity> findAll(BuildingSearchBuilder buildingBuilder) {

        //JPQL
//        StringBuilder sql = new StringBuilder("SELECT b FROM BuildingEntity b ");
        StringBuilder sql = new StringBuilder("SELECT DISTINCT b FROM BuildingEntity b ");
        joinTable(buildingBuilder, sql);
        StringBuilder where = new StringBuilder("WHERE 1 = 1");
        Map<String, Object> params = new HashMap<>();
        queryNormal(buildingBuilder, where, params);
        querySpecial(buildingBuilder, where, params);
        sql.append(where);
        Query q = entityManager.createQuery(sql.toString(), BuildingEntity.class);

        params.forEach(q::setParameter);

//        //SQL Native
//        String sql = "SELECT * FROM building b WHERE b.name like '%building%'";
//        Query q = entityManager.createNativeQuery(sql, BuildingEntity.class);
        return q.getResultList();
    }
}
