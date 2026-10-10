package co.edu.uniminuto.controller;
//Define que este archivo pertenece al paquete controller
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

import co.edu.uniminuto.entity.Grade;
import co.edu.uniminuto.services.GradeService;
//trae las anotaciones de spring web para mapear solicitudes HTTP y convertir datos automáticamente entre Java y formato JSON

@RestController
//Le indica a Spring que la clase es un controlador de la API REST y que la respuesta de cada método se serializará directamente a JSON.
@RequestMapping("/grades")
public class GradeController {
	//define el endpoint base para todas las operaciones relacionadas con las calificaciones.

    @Autowired
    private GradeService gradeService;
    //Inyecta la dependencia del service para invocar los métodos de negocio

    @GetMapping
    public List<Grade> getAllGrades() {
        return gradeService.findAll();
        //Mapea las solicitudes GET y retorna la lista completa de calificaciones almacenadas en la base de datos 
    }

    @GetMapping("/{id}")
    //Captura el parámetro enviado en la URL
    public Grade getGradeById(@PathVariable int id) {
        return gradeService.findById(id).orElse(null);
        //Asigna el parámetro al argumento int id del método y retorna null si no existe 
    }

    @PostMapping
    //Procesa solicitudes de creación POST
    public Grade createGrade(@RequestBody Grade grade) {
        return gradeService.save(grade);
        //Recibe el cuerpo de la petición y guarda la nueva nota en la base de datos 
    }

    @PutMapping
    //mapea solicitudes PUT
    public Grade updateGrade(@RequestBody Grade grade) {
        return gradeService.save(grade);
        //Modifica los campos de una nota existente
    }

    @DeleteMapping("/{id}")
    //atiende peticiones DELETE
    public void deleteGrade(@PathVariable int id) {
        gradeService.deleteById(id);
        //Borra la nota identificada con ese ID en la base de datos
    }
}