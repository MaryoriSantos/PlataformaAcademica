package co.edu.uniminuto.services;
//muestra que pertenece al paquete services 

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniminuto.entity.Deliverable;
import co.edu.uniminuto.repository.DeliverableRepository;

@Service
public class DeliverableService {
	//Anotacion de spring que declara esta clase como un componente de servicio permitiendo administrarlo e inyectarlo en otras capas

    @Autowired
    private DeliverableRepository deliverableRepository;
    //Inyecta automaticamente la interfaz del repositorio para interactuar con la base de datos 

    public List<Deliverable> findAll() {
        return deliverableRepository.findAll();
        //llama y usa el findAll para obtener una lista completa de los entregables registrados 
    }

    public Optional<Deliverable> findById(int id) {
        return deliverableRepository.findById(id);
        //Busca un entregable por su ID unico y retorna un objeto optional para evitar errores  en caso de que el registro no exitsa
    }

    public Deliverable save(Deliverable deliverable) {
        return deliverableRepository.save(deliverable);
        //Guarda un nuevo entregable o lo actualiza si ya esta existente en la base de datos
    }

    public void deleteById(int id) {
        deliverableRepository.deleteById(id);
        //elimina un entregable especifico de la base de datos mediante su ID
    }
}