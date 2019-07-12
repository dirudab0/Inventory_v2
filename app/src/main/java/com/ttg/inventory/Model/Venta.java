package com.ttg.inventory.Model;

import java.util.HashMap;
import java.util.Map;

public class Venta {

    private String uid;
    private String codigo;
    private String nombre;
    private String cliente;
    private String fpago;
    private String cantidad;
    private String valor;
    private String descripcion;

    public Venta() {
    }

    public String getUid() {
        return uid;
    }

    public Venta(String uid, String codigo, String nombre, String cliente, String fpago, String cantidad, String valor, String descripcion) {
        this.uid = uid;
        this.codigo = codigo;
        this.nombre = nombre;
        this.cliente = cliente;
        this.fpago = fpago;
        this.cantidad = cantidad;
        this.valor = valor;
        this.descripcion = descripcion;
    }

    public Venta(String codigo, String nombre, String cliente, String fpago, String cantidad, String valor, String descripcion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.cliente = cliente;
        this.fpago = fpago;
        this.cantidad = cantidad;
        this.valor = valor;
        this.descripcion = descripcion;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getFpago() {
        return fpago;
    }

    public void setFpago(String fpago) {
        this.fpago = fpago;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Map<String, Object> toMap() {

        HashMap<String, Object> result = new HashMap<>();

        result.put("codigo",this.codigo);
        result.put("nombre",this.nombre);
        result.put("cliente",this.cliente);
        result.put("fpago",this.fpago);
        result.put("cantidad",this.cantidad);
        result.put("valor",this.valor);
        result.put("descripcion",this.descripcion);

        return result;
    }
}
