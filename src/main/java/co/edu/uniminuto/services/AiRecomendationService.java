package co.edu.uniminuto.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.uniminuto.entity.AiRecomendation;
import co.edu.uniminuto.repository.AiRecomendationRepository;

// Identifica esta clase como componente de la capa de servicio.
@Service
public class AiRecomendationService implements AiRecomendationServiceI {

    // Inyecta el repositorio para acceder a los datos.
    @Autowired
    AiRecomendationRepository aiRecomendationRepository;

    @Override
    public AiRecomendation insertAiRecomendation(AiRecomendation aiRecomendation) {
        // Delega el registro al repositorio.
        return aiRecomendationRepository.insertAiRecomendation(aiRecomendation);
    }

    @Override
    public AiRecomendation updateAiRecomendation(AiRecomendation aiRecomendation) {
        // Actualiza solamente si el registro ya existe.
        if (aiRecomendationRepository.findByAiRecomendation(aiRecomendation.getRecommendationId()) != null) {
            return aiRecomendationRepository.updateAiRecomendation(aiRecomendation);
        }
        // Retorna null si no existe.
        return null;
    }

    @Override
    public int deleteAiRecomendation(AiRecomendation aiRecomendation) {
                // Comprueba que el registro exista antes de eliminarlo.
if (aiRecomendationRepository.findByAiRecomendation(aiRecomendation.getRecommendationId()) != null) {
    return aiRecomendationRepository.deleteAiRecomendation(aiRecomendation);
}
// Retorna cero si no existe.
return 0;
    }

    @Override
    public AiRecomendation findByAiRecomendation(int recommendationId) {
        // Delega la busqueda al repositorio.
        return aiRecomendationRepository.findByAiRecomendation(recommendationId);
    }

    @Override
    public List<AiRecomendation> findAll() {
        // Delega la consulta al repositorio.
        return aiRecomendationRepository.findAll();
    }
}
