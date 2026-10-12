package co.edu.uniminuto.repository;

import java.util.List;
import co.edu.uniminuto.entity.AiRecomendation;

// Define las operaciones de acceso a datos para AI recommendations.
public interface AiRecomendationRepositoryI {

    // Registra un nuevo registro.
    AiRecomendation insertAiRecomendation(AiRecomendation aiRecomendation);

    // Actualiza un registro existente.
    AiRecomendation updateAiRecomendation(AiRecomendation aiRecomendation);

    // Elimina el registro o guarda su estado de baja, segun la entidad.
    int deleteAiRecomendation(AiRecomendation aiRecomendation);

    // Busca un registro por su identificador.
    AiRecomendation findByAiRecomendation(int recommendationId);

    // Consulta todos los registros.
    List<AiRecomendation> findAll();
}
