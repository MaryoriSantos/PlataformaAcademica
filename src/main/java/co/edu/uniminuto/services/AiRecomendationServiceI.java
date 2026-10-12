package co.edu.uniminuto.services;

import java.util.List;
import co.edu.uniminuto.entity.AiRecomendation;

// Define las operaciones de negocio disponibles para AI recommendations.
public interface AiRecomendationServiceI {

    // Registra un nuevo registro.
    AiRecomendation insertAiRecomendation(AiRecomendation aiRecomendation);

    // Actualiza un registro existente.
    AiRecomendation updateAiRecomendation(AiRecomendation aiRecomendation);

    // Elimina o desactiva un registro.
    int deleteAiRecomendation(AiRecomendation aiRecomendation);

    // Busca un registro por identificador.
    AiRecomendation findByAiRecomendation(int recommendationId);

    // Consulta todos los registros.
    List<AiRecomendation> findAll();
}
