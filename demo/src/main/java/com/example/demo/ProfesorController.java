package com.example.demo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/profesores")
public class ProfesorController {

    private List<Profesor> profesores = new ArrayList<>();

    // Endpoint para obtener todos los profesores
    @GetMapping
    public List<Profesor> getAllProfesores() {
        return profesores;
    }

    // Endpoint para obtener un profesor por ID
    @GetMapping("/{id}")
    public ResponseEntity<Profesor> getProfesorById(@PathVariable int id) {
        for (Profesor profesor : profesores) {
            if (profesor.getId() == id) {
                return new ResponseEntity<>(profesor, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Endpoint para crear un nuevo profesor
    @PostMapping
    public ResponseEntity<Profesor> addProfesor(@RequestBody Profesor profesor) {
        profesores.add(profesor);
        return new ResponseEntity<>(profesor, HttpStatus.CREATED);
    }

    // Endpoint para actualizar un profesor existente
    @PutMapping("/{id}")
    public ResponseEntity<Profesor> updateProfesor(@PathVariable int id, @RequestBody Profesor updatedProfesor) {
        for (Profesor profesor : profesores) {
            if (profesor.getId() == id) {
                profesor.setNumeroEmpleado(updatedProfesor.getNumeroEmpleado());
                profesor.setNombres(updatedProfesor.getNombres());
                profesor.setApellidos(updatedProfesor.getApellidos());
                profesor.setHorasClase(updatedProfesor.getHorasClase());
                return new ResponseEntity<>(profesor, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Endpoint para eliminar un profesor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfesor(@PathVariable int id) {
        for (Profesor profesor : profesores) {
            if (profesor.getId() == id) {
                profesores.remove(profesor);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
