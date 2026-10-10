package co.edu.uniminuto.controller;
//Colocal la clase dentro del paquete controller
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

import co.edu.uniminuto.entity.NrcEnrollment;
import co.edu.uniminuto.services.NrcEnrollmentService;

@RestController
//le señala a spring que la clase respondera peticiones web de la ApiRest
@RequestMapping("/nrc-enrollments")
public class NrcEnrollmentController {
	//establece la ruta base de la Api 

    @Autowired
    private NrcEnrollmentService nrcEnrollmentService;
    //inyecta la dependencia del servicio 

    @GetMapping
    //responde las peticiones GET 
    public List<NrcEnrollment> getAllEnrollments() {
        return nrcEnrollmentService.findAll();
        //Invoca NrcEnrollmenService y regresa un arreglo con todas las  inscripciones grabadas t
    }

    @GetMapping("/{id}")
    //Captura el parametro de la URL 
    public NrcEnrollment getEnrollmentById(@PathVariable int id) {
        return nrcEnrollmentService.findById(id).orElse(null);
        //Maapea el valor de la ruta URL ID a la variable int id del metodo java
        //retorna null si no existe en la base de datos 
    }

    @PostMapping
    //Responde a solicitudes de inserccion post
    public NrcEnrollment createEnrollment(@RequestBody NrcEnrollment nrcEnrollment) {
        return nrcEnrollmentService.save(nrcEnrollment);
        //Deserializa el cuerpo de la peticion HTTP y lo convierte en un objeto NrcEnrollment de java 
    }

    @PutMapping
    //responde a peticiones PUT
    public NrcEnrollment updateEnrollment(@RequestBody NrcEnrollment nrcEnrollment) {
        return nrcEnrollmentService.save(nrcEnrollment);
        //actualiza los campos de registro de una matricula en la base de datos utilizabdo el metodo Save 
    }

    @DeleteMapping("/{id}")
    //atiende peticiones de borrado DELETE
    public void deleteEnrollment(@PathVariable int id) {
        nrcEnrollmentService.deleteById(id);
        //Elimina el registro especificado en la base de datos mediente la inyeccion del servicio 
    }
}