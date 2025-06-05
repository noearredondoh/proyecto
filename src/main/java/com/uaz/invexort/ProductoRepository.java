package com.uaz.invexort;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository {
    public Producto getProducto(long id);
    public List<Producto> getProductos();
    public List<Producto> getProductosStock();
    public int agregarProducto(Producto producto);
    public void eliminarProducto(Producto producto);
    public void actualizarProducto(Producto producto);
    public boolean actualizarStock(int idProducto, int nuevoStock);

}
