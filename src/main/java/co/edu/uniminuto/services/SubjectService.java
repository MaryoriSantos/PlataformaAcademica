package co.edu.uniminuto.services;
//Carpeta donde esta el archivo de la arquitectura del proyecto 

import java.util.List;
import java.util.Optional;
//clases nativas de java para manejar colecciones de elementos y valores nulos 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//inyeccion de dependencias y marcado de capas 

import co.edu.uniminuto.entity.Subject;
import co.edu.uniminuto.repository.SubjectRepository;
//entidad de la asignatura y conexion con la base de datos

@Service
public class SubjectService {
//logica de negocio
	
    @Autowired
    private SubjectRepository subjectRepository;
    //inyeccion de dependencias, crea conexion con la base de datos 

    public List<Subject> findAll() {
        return subjectRepository.findAll();
        //llama al metodo del repositorio 
    }

    public Optional<Subject> findById(int id) {
        return subjectRepository.findById(id);
        //Busca en la base de datos por ID y devuelve la materia si existe 
    }

    public Subject save(Subject subject) {
        return subjectRepository.save(subject);
        //guarda o actualiza una asignatura
    }

    public void deleteById(int id) {
        subjectRepository.deleteById(id);
        //elimina la materia del ID recibido
    }
}