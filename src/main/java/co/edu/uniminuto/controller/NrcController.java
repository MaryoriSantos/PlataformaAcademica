package co.edu.uniminuto.controller;
//define que este paquetehace parte de controladores

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

import co.edu.uniminuto.entity.Nrc;
import co.edu.uniminuto.services.NrcService;

@RestController
//le indica a spring que esta clase expone un servicio rest
@RequestMapping("/nrc")
public class NrcController {
	//Define el endpoint base

    @Autowired
    private NrcService nrcService;
    //inyecta la instancia para delegar las operaciones sobre la base de datos 
    //Separa las responsabilidades entre el controlador y el servicio

    @GetMapping
    public List<Nrc> getAllNrcs() {
        return nrcService.findAll();
        //retorna la lista de todos los NRC almacenados 
    }

    @GetMapping("/{id}")
    public Nrc getNrcById(@PathVariable int id) {
        return nrcService.findById(id).orElse(null);
        //Extrae el valor enviado en el segmento de ID de la url y lo asa como variable
        //si no existe el regidtro retorna NULL
    }

    @PostMapping
    public Nrc createNrc(@RequestBody Nrc nrc) {
        return nrcService.save(nrc);
        //Convierte el objeto enviado en el cuerpo de la peticion a una instacia de NRC
        //Llama al servivio para registrarlo 
    }

    @PutMapping
    public Nrc updateNrc(@RequestBody Nrc nrc) {
        return nrcService.save(nrc);
        //recibe la informacion modificada del objeto Nrc, como este incluye su clave primaria actualiza los datos del registro existente 
    }

    @DeleteMapping("/{id}")
    public void deleteNrc(@PathVariable int id) {
        nrcService.deleteById(id);
        //Recibe el ID por medio de la URL y remueve el registro correspondiente de la base de datos a traves del servicio
    }
}