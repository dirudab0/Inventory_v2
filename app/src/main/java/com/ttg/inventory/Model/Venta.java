package com.ttg.inventory.Model;

import java.util.HashMap;
import java.util.Map;

public class Venta {

    private String uid;
    private String codigo;
    private String producto;
    private String cliente;
    private String fpago;
    private String cantidad;
    private String valor;

    public Venta() {
    }

    public String getUid() {
        return uid;
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

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
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

    public Map<String, Object> toMap() {

        HashMap<String, Object> result = new HashMap<>();
        result.put("uid",this.uid);
        result.put("codigo",this.codigo);
        result.put("producto",this.producto);
        result.put("cliente",this.cliente);
        result.put("fpago",this.fpago);
        result.put("cantidad",this.cantidad);
        result.put("valor",this.valor);


        return result;
    }
}
