package com.report.catalog.controllers;


import java.util.List;
import java.util.stream.Collectors;

import com.report.catalog.exception.ResourceNotFoundException;
import com.report.catalog.models.Reporte;
import com.report.catalog.repositories.ReporteRepository;
import com.report.catalog.tos.ReporteTo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;






@RestController
@RequestMapping(path = "catalogo")
public class ReporteController {
    private static Logger logger =  LoggerFactory.getLogger(ReporteController.class);

    @Autowired
    ReporteRepository reporteRepositosy;

    @GetMapping(value="/reporte")
    public List<ReporteTo> getAllReports() {
        logger.info("Consultando todos los reportes...");
        return this.reporteRepositosy.findAll().stream()
                    .map(rep -> new ReporteTo(rep.getId(), rep.getNombreResponsable(), rep.getArea(),
                     rep.getTitulo(), rep.getFechaReporte(),rep.getNameFile(), rep.getFechaUploadReporte()))
                    .collect(Collectors.toList());
        
    }

    @GetMapping(value="/reporte/{id}")
    public ReporteTo getReportById(@PathVariable Integer id) throws ResourceNotFoundException {
        logger.info("Consultando el reporte {}", id);
        return this.reporteRepositosy.findById(id)
                .map(rep -> new ReporteTo(rep.getId(), rep.getNombreResponsable(),rep.getArea(), 
                rep.getTitulo(),rep.getFechaReporte(), rep.getNameFile(), rep.getFechaUploadReporte()))
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro el reporte con id " + id));
        
    }

    @PostMapping(value="/reporte")
    public ReporteTo addReporte(@RequestBody Reporte reporte) {
        logger.info("Agregando un nuevo reporte");
        Reporte repBd =  this.reporteRepositosy.save(reporte);
        logger.info("Reporte guardado correctamente con id {}", repBd.getId());
        return new ReporteTo(repBd.getId(), repBd.getNombreResponsable(),repBd.getArea(), 
            repBd.getTitulo(), repBd.getFechaReporte(), repBd.getNameFile(), repBd.getFechaUploadReporte());
        
    }
    
    
    
}
