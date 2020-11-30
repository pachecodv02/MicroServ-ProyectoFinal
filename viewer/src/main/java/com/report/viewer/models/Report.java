package com.report.viewer.models;




public class Report {
    private Integer cantidad;
    private String descripcion;
    private Float precio;
    private Float total;

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Report() {
    }

    public Report(Integer cantidad, String descripcion, Float precio, Float total) {
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.precio = precio;
        this.total = total;
    }
    
}
