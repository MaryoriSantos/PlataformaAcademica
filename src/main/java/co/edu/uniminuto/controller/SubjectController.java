package co.edu.uniminuto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uniminuto.entity.Subject;
import co.edu.uniminuto.services.SubjectService;

@RestController
//Indica que respodera peticiones web 
@RequestMapping("/subject")
//define la URL base del modulo
public class SubjectController {
	

    @Autowired
    private SubjectService subjectService;
    //inyecta la clase del service 

    @GetMapping
    public List<Subject> getAllSubjects() {
        return subjectService.findAll();
        //devuelve la lista completa de asignaturas con la peticion GET
    }

    @GetMapping("/{id}")
    public Subject getSubjectById(@PathVariable int id) {
        return subjectService.findById(id).orElse(null);
        //Busca la asignatura con el ID si no esta retoma null
    }

    @PostMapping
    public Subject createSubject(@RequestBody Subject subject) {
        return subjectService.save(subject);
        //Toma el cuerpo de la peticion y lo convierte en una clase subject para la base de datos 
    }

    @PutMapping
    public Subject updateSubject(@RequestBody Subject subject) {
        return subjectService.save(subject);
        //recibe el objeto con los datos modificados y su ID correspondiente el metodo save lo detecta y se actualiza la base de datos
    }

    @DeleteMapping("/{id}")
    public void deleteSubject(@PathVariable int id) {
        subjectService.deleteById(id);
        //Recibe el ID por la URL y elimina ese registro de la base de datos 
    }
}