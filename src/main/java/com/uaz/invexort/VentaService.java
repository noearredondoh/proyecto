package com.uaz.invexort;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class VentaService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<VentasMes> obtenerVentasMensuales() {
        String sql = "SELECT MONTH(fecha) as mes, SUM(subtotal) as total FROM detalle_venta GROUP BY mes";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            int numeroMes = rs.getInt("mes");
            double total = rs.getDouble("total");
            String nombreMes = obtenerNombreMes(numeroMes);
            return new VentasMes(nombreMes, total);
        });
    }

    public String obtenerNombreMes(int mes) {
        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        return meses[mes - 1];
    }
}
