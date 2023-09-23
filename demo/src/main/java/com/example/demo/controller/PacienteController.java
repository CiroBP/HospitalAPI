package com.example.demo.controller;

import com.example.demo.model.Paciente;
import com.example.demo.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    public PacienteService ps;

    @GetMapping("")
    public List<Paciente> getAll(){
        return ps.getAll();
    }

    @PostMapping("")
    public ResponseEntity add(@RequestBody Paciente p){
        return ps.add(p);
    }

    @PostMapping("/{id}/update")
    public ResponseEntity update(@PathVariable int id ,@RequestBody  Paciente p){
        return ps.update(id,p);
    }
    @PostMapping("/{id}/delete")
    public ResponseEntity delete(@PathVariable int id){
        return ps.delete(id);
    }

}
