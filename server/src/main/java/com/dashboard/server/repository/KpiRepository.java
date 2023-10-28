package com.dashboard.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dashboard.server.entity.KPI.Kpi;

public interface KpiRepository extends JpaRepository<Kpi, String> {

}
