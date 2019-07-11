package com.ttg.inventory.Model;

import java.util.HashMap;
import java.util.Map;

public class Iinicial {

    private String uid;
    private String nombre;
    private String contacto;
    private String correo;
    private String telefono;
    private String iadicional;

    public Iinicial() {
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

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
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
        result.put("uid",this.uid);
        result.put("nombre",this.nombre);
        result.put("contacto",this.contacto);
        result.put("correo",this.correo);
        result.put("telefono",this.telefono);
        result.put("iadicional",this.iadicional);

        return result;
    }

}
