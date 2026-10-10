package co.edu.uniminuto.services;
//define que este archivo pertenece al paquete de servicios

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniminuto.entity.NrcEnrollment;
import co.edu.uniminuto.repository.NrcEnrollmentRepository;

@Service
public class NrcEnrollmentService {
	//Le indica a spring que estta clase contiene la logica de negocios

    @Autowired
    private NrcEnrollmentRepository nrcEnrollmentRepository;
    //aplica el patron de inyeccion de dependencias. Inyecta la interfaz 

    public List<NrcEnrollment> findAll() {
        return nrcEnrollmentRepository.findAll();
        //llama al metodo findAll del repositorio para consultar y retornar la lista completa de matriculas  
    }

    public Optional<NrcEnrollment> findById(int id) {
        return nrcEnrollmentRepository.findById(id);
        //busca un registro por su clave primaria, devuelve un objeto optional para evitar errores en caso del que el Id no exista
    }

    public NrcEnrollment save(NrcEnrollment nrcEnrollment) {
        return nrcEnrollmentRepository.save(nrcEnrollment);
        //recibe un objeto y si no tiene ID asignado crea un nuevo registro, y si ya es existente actualiza los datos en la base 
    }

    public void deleteById(int id) {
        nrcEnrollmentRepository.deleteById(id);
        //Recibe uuna matricula por ID y llama el metodo DELETE para borrar el registro correspondiente en la base de datos 
    }
}