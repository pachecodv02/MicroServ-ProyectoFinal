package com.report.viewer.controllers;

import java.util.List;

import com.report.viewer.models.FileReport;
import com.report.viewer.repositories.FileReportRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestMapping;




@RestController
@RequestMapping(path = "viewer")
public class ReporteViewerController {
    
    @Autowired
    FileReportRepository repository;

    @GetMapping(value="/reporte")
    public Iterable<FileReport> getAllReports() {

        return this.repository.findAll();
    }

    @GetMapping(value="/reporte/{id}")
    public FileReport getReportBiId(@PathVariable String id) {

        return this.repository.findById(id).get();
    }

    @GetMapping(value="/reporte/idreporte/{idReport}")
    public FileReport getAllReports(@PathVariable String idReport) {

        return this.repository.findByIdReport(idReport).get();
    }
    
}
