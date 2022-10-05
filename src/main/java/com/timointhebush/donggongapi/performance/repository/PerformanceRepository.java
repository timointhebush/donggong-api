package com.timointhebush.donggongapi.performance.repository;

import com.timointhebush.donggongapi.performance.model.Performance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformanceRepository extends JpaRepository<Performance, Long> {
}
