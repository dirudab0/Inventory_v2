package com.ttg.inventory.Model;

import java.util.HashMap;
import java.util.Map;

public class Bodega {

    private String uid;
    private String nombre;
    private String direccion;
    private String correo;
    private String telefono;
    private String iadicional;

    public Bodega() {
    }

    public Bodega(String uid, String nombre, String direccion, String correo, String telefono, String iadicional) {
        this.uid = uid;
        this.nombre = nombre;
        this.direccion = direccion;
        this.correo = correo;
        this.telefono = telefono;
        this.iadicional = iadicional;
    }

    public Bodega(String nombre, String direccion, String correo, String telefono, String iadicional) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.correo = correo;
        this.telefono = telefono;
        this.iadicional = iadicional;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getIadicional() {
        return iadicional;
    }

    public void setIadicional(String iadicional) {
        this.iadicional = iadicional;
    }

    public Map<String, Object> toMap() {

        HashMap<String, Object> result = new HashMap<>();

        result.put("nombre",this.nombre);
        result.put("direccion",this.direccion);
        result.put("correo",this.correo);
        result.put("telefono",this.telefono);
        result.put("iadicional",this.iadicional);

        return result;
    }
}
