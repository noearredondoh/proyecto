package com.uaz.invexort;

public class Producto {
    private long id;
    private String nombre;
    private String categoria;
    private int stock;
    private String fecha;
    private float precio;
    public Producto() {

    }

    public Producto(String nombre, String categoria, int stock, String fecha, float precio) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.stock = stock;
        this.fecha = fecha;
        this.precio = precio;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getStock() {
        return stock;
    }

    public String getFecha() {
        return fecha;
    }
}
