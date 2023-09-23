package com.example.demo.controller;


import com.example.demo.model.Medico;
import com.example.demo.model.PacienteDTO;
import com.example.demo.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    public MedicoService ms;

    @GetMapping("")
    public List<Medico> getAll(){
        return ms.getAll();
    }
    @PostMapping("")
    public ResponseEntity add(@RequestBody Medico m){
        return ms.add(m);
    }

    @PostMapping("/{id}/update")
    public ResponseEntity update(@PathVariable int id ,@RequestBody  Medico m){
        return ms.update(id,m);
    }
    @PostMapping("/{id}/delete")
    public ResponseEntity delete(@PathVariable int id){
        return ms.delete(id);
    }

    @GetMapping("/{id}/getMedicoPacientes")
    public List<PacienteDTO> getMedicoPacientes(@PathVariable Integer id){
        return ms.getMedicoPacientes(id);
    }

}
