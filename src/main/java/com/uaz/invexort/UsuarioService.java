package com.uaz.invexort;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UsuarioService implements UsuarioRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Usuario findByCredenciales(String correo, String contrasena) {
        Usuario usuario = null;
        String sql = "SELECT id, nombre, correo FROM usuarios WHERE correo = ? AND contrasena = ?";

        try {
            usuario = jdbcTemplate.queryForObject(sql, new Object[]{correo, contrasena}, (rs, rowNum) -> {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNombre(rs.getString("nombre"));
                u.setCorreo(rs.getString("correo"));
                return u;
            });

            // Aquí puedes usar el usuario
        } catch (EmptyResultDataAccessException e) {
            // No se encontró ningún usuario con esas credenciales
            usuario = null;
        }
        return usuario;
    }

    @Override
    public List<Usuario> findAll() {
        return List.of();
    }
}
