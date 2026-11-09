package com.example.demo.repository;

import com.example.demo.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    List<Consulta> findByMedicoUsername(String username);
    List<Consulta> findByPacienteUsername(String username);
}
