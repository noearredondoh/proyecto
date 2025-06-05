package com.uaz.invexort;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleVentaRepository {
    List<DetalleVenta> obtenerDetalleVentas();
    int agregarDetalleVenta(DetalleVenta detalleVenta);
}
