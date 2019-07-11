package com.ttg.inventory.Model;

import java.util.HashMap;
import java.util.Map;

public class Gasto {

    private String uid;
    private String nombre;
    private String fecha;
    private String tgasto;
    private String fpago;
    private String descripcion;

    public Gasto() {
    }

    public Gasto(String uid, String nombre, String fecha, String tgasto, String fpago, String descripcion) {
        this.uid = uid;
        this.nombre = nombre;
        this.fecha = fecha;
        this.tgasto = tgasto;
        this.fpago = fpago;
        this.descripcion = descripcion;
    }

    public Gasto(String nombre, String fecha, String tgasto, String fpago, String descripcion) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.tgasto = tgasto;
        this.fpago = fpago;
        this.descripcion = descripcion;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTgasto() {
        return tgasto;
    }

    public void setTgasto(String tgasto) {
        this.tgasto = tgasto;
    }

    public String getFpago() {
        return fpago;
    }

    public void setFpago(String fpago) {
        this.fpago = fpago;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Map<String, Object> toMap() {

        HashMap<String, Object> result = new HashMap<>();

        result.put("nombre",this.nombre);
        result.put("fecha",this.fecha);
        result.put("tgasto",this.tgasto);
        result.put("fpago",this.fpago);
        result.put("descripcion",this.descripcion);

        return result;
    }

}
