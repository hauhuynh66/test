package com.house.repository;

import com.house.model.SensorValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorValueRepository extends JpaRepository<SensorValue, Integer> {
}
