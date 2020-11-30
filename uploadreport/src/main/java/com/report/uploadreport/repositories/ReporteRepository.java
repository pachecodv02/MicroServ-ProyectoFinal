package com.report.uploadreport.repositories;


import com.report.uploadreport.models.Reporte;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReporteRepository extends JpaRepository<Reporte, Integer>{
    
}
