package com.report.catalog.repositories;

import com.report.catalog.models.Reporte;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReporteRepository extends JpaRepository<Reporte, Integer>{
    
}
