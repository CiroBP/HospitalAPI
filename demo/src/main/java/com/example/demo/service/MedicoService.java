package com.example.demo.service;

import com.example.demo.model.Medico;
import com.example.demo.model.Paciente;
import com.example.demo.model.PacienteDTO;
import com.example.demo.repository.MedicoRepository;
import com.example.demo.repository.PacienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Service
public class MedicoService {
    @Autowired
    private MedicoRepository mr;
    @Autowired
    private PacienteRepository pr;

    private final ModelMapper mm = new ModelMapper();

    public MedicoService(MedicoRepository mr, PacienteRepository pr){
        this.mr = mr;
        this.pr = pr;
    }

    public List<Medico> getAll(){
        return mr.findAll();
    }

    public ResponseEntity add(Medico m){
        try {
            mr.save(m);
            return ResponseEntity.status(CREATED).build();
        } catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity update(int id, Medico m) {
        try {
            Medico me = mr.findById(id).get();
            me.setNombre(m.getNombre());
            me.setPacientes(m.getPacientes());
            mr.save(me);
            return ResponseEntity.status(OK).build();
        } catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity delete(int id) {
        try {
            mr.deleteById(id);
            return ResponseEntity.status(OK).build();
        } catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    public List<PacienteDTO> getMedicoPacientes(Integer MedicoId){
        List<PacienteDTO> PacienteList = new ArrayList<>();
        List<Integer> MedicoPacientes = mr.getMedicoPacientes(MedicoId);
        for(int pid : MedicoPacientes){
            Paciente paciente = pr.findById(pid).orElseThrow(() -> new HttpClientErrorException(BAD_REQUEST));
            PacienteList.add(mm.map(paciente, PacienteDTO.class));
        }
        return PacienteList;
    }

}
