package com.arstialmq.javaweb.repository;

import com.arstialmq.javaweb.repository.entity.DistrictEntity;

public interface DistrictRepository {
    DistrictEntity findNameById(Long id);
}
