package com.uaz.invexort;

public class DetalleVenta {
    private int id;
    private String producto;
    private int cantidad;
    private float precio;
    private float subtotal;

    public DetalleVenta() {

    }

    public DetalleVenta(float subtotal, float precio, int cantidad, String producto) {
        this.subtotal = subtotal;
        this.precio = precio;
        this.cantidad = cantidad;
        this.producto = producto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }
}

