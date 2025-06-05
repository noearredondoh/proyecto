package com.uaz.invexort;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository {
    public Usuario findByCredenciales(String correo, String contrasena);
    public List<Usuario> findAll();

}
