package com.ttg.inventory.Model;

import java.util.HashMap;
import java.util.Map;

public class Producto {

    private String uid;
    private String nombre;
    private String codigo;
    private String creserva;
    private String vcompra;
    private String cantidad;
    private String vventa;
    private String unidad;
    private String categoria;
    private String observacion;

    public Producto() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCreserva() {
        return creserva;
    }

    public void setCreserva(String creserva) {
        this.creserva = creserva;
    }

    public String getVcompra() {
        return vcompra;
    }

    public void setVcompra(String vcompra) {
        this.vcompra = vcompra;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getVventa() {
        return vventa;
    }

    public void setVventa(String vventa) {
        this.vventa = vventa;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }


    public Map<String, Object> toMap() {

        HashMap<String, Object> result = new HashMap<>();
        result.put("uid",this.uid);
        result.put("nombre",this.nombre);
        result.put("codigo",this.codigo);
        result.put("creserva",this.creserva);
        result.put("vcompra",this.vcompra);
        result.put("cantidad",this.cantidad);
        result.put("vventa",this.vventa);
        result.put("unidad",this.unidad);
        result.put("categoria",this.categoria);
        result.put("observacion",this.observacion);

        return result;
    }

}
