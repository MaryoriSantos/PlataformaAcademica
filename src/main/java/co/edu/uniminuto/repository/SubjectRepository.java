package co.edu.uniminuto.repository;
//Define la carpeta donde esta el archivo

import org.springframework.data.jpa.repository.JpaRepository;
//Trae las herramientas del JPA
import org.springframework.stereotype.Repository;
// Le indica a Spring que esta interfaz maneja el acceso a la base de datos 
import co.edu.uniminuto.entity.Subject;
//importa la entidad de la tabla a tranbajar 
@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
}//le ordena a la interfaz heredar los metodos, la tabla que va a manipular y el tipo de dato de la clave primaria 