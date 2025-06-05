package com.uaz.invexort;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductoService implements ProductoRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Producto getProducto(long id) {
        String sql = "SELECT * FROM productos WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(
                    sql,
                    new Object[]{id},
                    new BeanPropertyRowMapper<>(Producto.class)
            );
            } catch (EmptyResultDataAccessException e) {
                // Si no encuentra nada, puedes devolver null o lanzar una excepción personalizada
                return null;
            }
    }

    @Override
    public List<Producto> getProductos() {
        String sql = "SELECT * FROM productos";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Producto p = new Producto();
            p.setId(rs.getInt("id"));
            p.setNombre(rs.getString("nombre"));
            p.setCategoria(rs.getString("categoria"));
            p.setStock(rs.getInt("stock"));
            p.setPrecio(rs.getFloat("precio"));
            p.setFecha(rs.getTimestamp("fecha").toString());
            return p;
        });
    }

    @Override
    public List<Producto> getProductosStock() {
        String sql = "SELECT * FROM productos where stock <= 2";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Producto p = new Producto();
            p.setId(rs.getInt("id"));
            p.setNombre(rs.getString("nombre"));
            p.setCategoria(rs.getString("categoria"));
            p.setStock(rs.getInt("stock"));
            p.setPrecio(rs.getFloat("precio"));
            p.setFecha(rs.getTimestamp("fecha").toString());
            return p;
        });
    }

    @Override
    public int agregarProducto(Producto producto) {
        String sql = "INSERT INTO productos (nombre, categoria, stock, fecha, precio) VALUES (?, ?, ?, ?,?)";
        return jdbcTemplate.update(sql, producto.getNombre(), producto.getCategoria(), producto.getStock(), producto.getFecha(), producto.getPrecio());
    }

    @Override
    public void eliminarProducto(Producto producto) {

    }

    @Override
    public void actualizarProducto(Producto producto) {

    }

    @Override
    public boolean actualizarStock(int idProducto, int nuevoStock) {
            String sql = "UPDATE productos SET stock = ? WHERE id = ?";
            int filas = jdbcTemplate.update(sql, nuevoStock, idProducto);
            return filas > 0;
    }
}

