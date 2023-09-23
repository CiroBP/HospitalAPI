package com.example.demo.repository;

import com.example.demo.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MedicoRepository extends JpaRepository<Medico,Integer> {
    @Query(value = "select paciente_id from medico_paciente where medico_id = :medico_id", nativeQuery = true)
    public List<Integer> getMedicoPacientes(Integer medico_id);
}
