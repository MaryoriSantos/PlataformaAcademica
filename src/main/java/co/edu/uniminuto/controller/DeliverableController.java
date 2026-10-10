package co.edu.uniminuto.controller;
//muestra que pertenece a la capa controller 


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

import co.edu.uniminuto.entity.Deliverable;
import co.edu.uniminuto.services.DeliverableService;

@RestController
//Indica a spring que la clase procesa peticiones Http 
@RequestMapping("/deliverables")
public class DeliverableController {
	//define la URL del recurso 

    @Autowired
    private DeliverableService deliverableService;
    //inyecta el servicio del service para delegarle la logica de negocio 

    @GetMapping
    public List<Deliverable> getAllDeliverables() {
        return deliverableService.findAll();
        //Responde a peticiones GET devolviendo la lista completa de entregables registradas en la base de datos 
    }

    @GetMapping("/{id}")
    public Deliverable getDeliverableById(@PathVariable int id) {
        return deliverableService.findById(id).orElse(null);
        // Usa @patchVariable para extraer el ID de la URL y buscar un entregable especifico 
    }

    @PostMapping
    public Deliverable createDeliverable(@RequestBody Deliverable deliverable) {
        return deliverableService.save(deliverable);
        //Usa @RequestBody para recibir un objeto JSON enviado por un cliente y guardarlo en la base de datos a traves del service 
    }

    @PutMapping
    public Deliverable updateDeliverable(@RequestBody Deliverable deliverable) {
        return deliverableService.save(deliverable);
        //Responde a solicitudes PUT actualizando los datos de un entregable existente en la base de datos 
    }

    @DeleteMapping("/{id}")
    public void deleteDeliverable(@PathVariable int id) {
        deliverableService.deleteById(id);
        //Responde a las solicitudes DELETE eliminando el registro correspondiente de la base de datos 
    }
}