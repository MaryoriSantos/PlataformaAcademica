package co.edu.uniminuto.services;
//Define que esta clase pertenece al paquete services 
import java.util.List;
import java.util.Optional;
//clases utilitarias de java que ayuda a gestionar valores que podrian ser nulos 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//anotaciones de spring framework para gestinar la inyeccion de dependencias y declarar componentes 

import co.edu.uniminuto.entity.Grade;
import co.edu.uniminuto.repository.GradeRepository;
//Clases del proyecto que representan la entidad de notas y la interfaz

@Service
public class GradeService {
	//indica que esta clase contiene la lógica de negocio y debe registrarse como un Bean en el contenedor de dependencias 

    @Autowired
    private GradeRepository gradeRepository;
    //aplica el patrón de Inyección de Dependencias e inyecta la interfaz del repositorio para delegarle las operaciones sobre la base de datos 

    public List<Grade> findAll() {
        return gradeRepository.findAll();
        //trae la lista completa de calificaciones registradas en la base de datos
    }

    public Optional<Grade> findById(int id) {
    	//Busca una nota específica mediante su clave primaria y evita errores en caso de que no se encuentre 
        return gradeRepository.findById(id);
    }

    public Grade save(Grade grade) {
        return gradeRepository.save(grade);
        //Si no existe un registro de ese ID inserta una nueva nota en la base de datos y si ya tiene ID actualiza el registro existente.
    }

    public void deleteById(int id) {
        gradeRepository.deleteById(id);
        //Elimina la nota correspondiente en la base de datos usando la clave primaria recibida
    }
}