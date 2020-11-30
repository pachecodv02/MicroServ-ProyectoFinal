package com.report.processfile.repositories;



import java.util.Optional;

import com.report.processfile.models.FileReport;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

// @EnableScan
public interface FileReportRepository extends CrudRepository<FileReport, String> {
    // Optional<FileReport> findById(String id);
}
