package com.report.viewer.repositories;


import java.util.Optional;

import com.report.viewer.models.FileReport;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface FileReportRepository extends CrudRepository<FileReport, String> {
    Optional<FileReport> findById(String id);
    Optional<FileReport> findByIdReport(String id);
}
