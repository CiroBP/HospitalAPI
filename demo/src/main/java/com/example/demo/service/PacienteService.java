package com.example.demo.service;

import com.example.demo.model.Paciente;
import com.example.demo.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository pr;


    public PacienteService(PacienteRepository pr){
        this.pr = pr;
    }

    public List<Paciente> getAll(){
        return pr.findAll();
    }

    public ResponseEntity add( Paciente p){
        try{
            pr.save(p);
            return ResponseEntity.status(CREATED).build();
        } catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity update(int id, Paciente p) {
        try {
            Paciente pa = pr.findById(id).get();
            pa.setNombre(p.getNombre());
            pa.setMedicos(p.getMedicos());
            pr.save(pa);
            return ResponseEntity.status(OK).build();
        } catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity delete(int id) {
        try {
            pr.deleteById(id);
            return ResponseEntity.status(OK).build();
        } catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }
}
