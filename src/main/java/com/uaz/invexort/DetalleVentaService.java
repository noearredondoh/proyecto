package com.uaz.invexort;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DetalleVentaService implements DetalleVentaRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<DetalleVenta> obtenerDetalleVentas() {
        String sql = "SELECT * FROM detalle_venta order by id";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(DetalleVenta.class));
    }

    @Override
    public int agregarDetalleVenta(DetalleVenta detalleVenta) {
        String sql = "INSERT INTO detalle_venta (producto, cantidad, precio, subtotal, fecha) VALUES (?, ?, ?, ?, CURDATE())";
        return jdbcTemplate.update(
                sql,
                detalleVenta.getProducto(),
                detalleVenta.getCantidad(),
                detalleVenta.getPrecio(),
                detalleVenta.getSubtotal()
        );
    }

}
