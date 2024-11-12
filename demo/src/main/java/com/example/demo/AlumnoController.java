package com.example.demo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {
    private List<Alumno> alumnos = new ArrayList<>();

    // Endpoint para obtener todos los alumnos
    @GetMapping
    public List<Alumno> getAllAlumnos() {
        return alumnos;
    }

    // Endpoint para obtener un alumno por ID
    @GetMapping("/{id}")
    public ResponseEntity<Alumno> getAlumnoById(@PathVariable int id) {
        for (Alumno alumno : alumnos) {
            if (alumno.getId() == id) {
                return new ResponseEntity<>(alumno, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Endpoint para crear un nuevo alumno
    @PostMapping
    public ResponseEntity<Alumno> addAlumno(@RequestBody Alumno alumno) {
        alumnos.add(alumno);
        return new ResponseEntity<>(alumno, HttpStatus.CREATED);
    }

    // Endpoint para actualizar un alumno existente
    @PutMapping("/{id}")
    public ResponseEntity<Alumno> updateAlumno(@PathVariable int id, @RequestBody Alumno updatedAlumno) {
        for (Alumno alumno : alumnos) {
            if (alumno.getId() == id) {
                alumno.setNombres(updatedAlumno.getNombres());
                alumno.setApellidos(updatedAlumno.getApellidos());
                alumno.setMatricula(updatedAlumno.getMatricula());
                alumno.setPromedio(updatedAlumno.getPromedio());
                return new ResponseEntity<>(alumno, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Endpoint para eliminar un alumno
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlumno(@PathVariable int id) {
        for (Alumno alumno : alumnos) {
            if (alumno.getId() == id) {
                alumnos.remove(alumno);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
