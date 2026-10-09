package co.edu.uniminuto.services;
//ubica el archivo en la capa de servicios

import java.util.List;
import java.util.Optional;
//tipo de datos de java y evita errores con valores nulos 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//Anotaciones de el framework spring 
import co.edu.uniminuto.entity.Nrc;
import co.edu.uniminuto.repository.NrcRepository;
//Entidad que representa la tabla Nrc y su repositorio

@Service
public class NrcService {
	//idica que actua como la capa de servicio donde reside la logica de negocio

    @Autowired
    private NrcRepository nrcRepository;
    //inyecta automaticamente el repostorio

    public List<Nrc> findAll() {
        return nrcRepository.findAll();
        //Llama al metodo findAll para consultar y retornar la lista completa de los registros de tabla
    }

    public Optional<Nrc> findById(int id) {
        return nrcRepository.findById(id);
        //recibe la clave primaria ID y busa un NRC especifico en la base de datos
        //retorna un contenedor optional si existe la entidad o vacio si no la encuentra 
    }

    public Nrc save(Nrc nrc) {
        return nrcRepository.save(nrc);
        //Guarda la entidad NRC en la base de datos 
        //si el objeto no tiene ID ejecuta un insert y si ya contiene uno existente realiza un update
    }

    public void deleteById(int id) {
        nrcRepository.deleteById(id);
        //Elimina el regidtro del ID proporcionado drectamente 
    }
}