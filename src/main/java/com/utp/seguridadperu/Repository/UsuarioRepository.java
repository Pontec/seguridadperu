package com.utp.seguridadperu.Repository;

import com.utp.seguridadperu.modelo.UsuarioModelo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioModelo, Long> {
    Optional<UsuarioModelo> findByCorreo(String email);
}
