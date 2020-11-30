package com.report.uploadreport.controllers;


import java.io.IOException;
import java.util.Date;


import com.report.uploadreport.exception.ResourceNotFoundException;
import com.report.uploadreport.models.Reporte;
import com.report.uploadreport.repositories.ReporteRepository;
import com.report.uploadreport.services.StorageS3Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
//@RequestMapping(path = "upload")
public class UploadReporteController {
    private static Logger logger = LoggerFactory.getLogger(UploadReporteController.class);

    @Autowired
    ReporteRepository reporteRepository;

    @Autowired
    StorageS3Service s3Service;

    @PostMapping(value = "/reporte/{id}/file")
    public String  uploadReporteById(@PathVariable Integer id,
            @RequestParam(name = "file", required = true) MultipartFile file)
            throws ResourceNotFoundException, IOException {
        if(!file.getOriginalFilename().toUpperCase().endsWith(".CSV") && !file.getOriginalFilename().toUpperCase().endsWith(".XLS") ){
            return "La extension del archivo es incorrecta";
        }
       logger.info("Subiendo el reporte del id {}", id);
       Reporte  rep = this.reporteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No se encontro el Id del Reporte"));
       String nameFile = rep.getId()+"_"+file.getOriginalFilename();
       rep.setNameFile(nameFile);
       Date  fechaUpload = new Date();
       rep.setFechaUploadReporte(fechaUpload);

       this.s3Service.storageFile(nameFile, file.getInputStream());
       logger.info("Reporte  {} cargado correctamente en S3", nameFile);

       reporteRepository.save(rep);
       logger.info("Nombre del reporte id {} guardado correctamente en BD", rep.getId());

       return "El archivo "+ nameFile+" se almaceno correctamente";

    }

    
    
    
}
