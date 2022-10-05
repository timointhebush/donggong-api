package com.timointhebush.donggongapi.performance.repository;

import com.timointhebush.donggongapi.performance.model.PerformanceFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformanceFileRepository extends JpaRepository<PerformanceFile, Long> {
}
