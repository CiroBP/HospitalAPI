package com.example.demo.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    @ManyToMany()
    @JoinTable(name = "medico_paciente", joinColumns = @JoinColumn(name = "paciente_id"), inverseJoinColumns = @JoinColumn(name = "medico_id"))
    private List<Medico> medicos;
}
