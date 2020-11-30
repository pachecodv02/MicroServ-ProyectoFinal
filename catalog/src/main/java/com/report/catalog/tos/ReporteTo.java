package com.report.catalog.tos;

import java.util.Date;



public class ReporteTo {

    
    private Integer id;
    
    private String nombreResponsable;
    private String area;
    private String titulo;
    private Date fechaReporte;
    private String nameFile;
    private Date fechaUploadReporte;
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreResponsable() {
        return nombreResponsable;
    }

    public void setNombreResponsable(String nombreResponsable) {
        this.nombreResponsable = nombreResponsable;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(Date fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }
    public Date getFechaUploadReporte() {
        return fechaUploadReporte;
    }

    public void setFechaUploadReporte(Date fechaUploadReporte) {
        this.fechaUploadReporte = fechaUploadReporte;
    }

    public ReporteTo() {
    }

    public ReporteTo(Integer id, String nombreResponsable, String area, String titulo, Date fechaReporte,
            String nameFile,Date fechaUploadReporte) {
        this.id = id;
        this.nombreResponsable = nombreResponsable;
        this.area = area;
        this.titulo = titulo;
        this.fechaReporte = fechaReporte;
        this.nameFile = nameFile;
        this.fechaUploadReporte = fechaUploadReporte;
    }

    

    

    
    
}
