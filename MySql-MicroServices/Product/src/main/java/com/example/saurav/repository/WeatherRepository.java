package com.example.saurav.repository;

import com.example.saurav.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepository extends JpaRepository<Weather, Long> {
    Weather findBylocationId(Long locationId);
}
