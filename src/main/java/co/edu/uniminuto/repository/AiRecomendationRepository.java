package co.edu.uniminuto.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import co.edu.uniminuto.entity.AiRecomendation;
import co.edu.uniminuto.jpa.JpaAiRecomendation;

// Identifica esta clase como componente de acceso a datos.
@Repository
public class AiRecomendationRepository implements AiRecomendationRepositoryI {

    // Inyecta JPA para acceder a la tabla correspondiente.
    @Autowired
    JpaAiRecomendation jpaAiRecomendation;

    @Override
    public AiRecomendation insertAiRecomendation(AiRecomendation aiRecomendation) {
        // Guarda un registro nuevo.
        return jpaAiRecomendation.save(aiRecomendation);
    }

    @Override
    public AiRecomendation updateAiRecomendation(AiRecomendation aiRecomendation) {
        // Guarda los cambios del registro.
        return jpaAiRecomendation.save(aiRecomendation);
    }

    @Override
    public int deleteAiRecomendation(AiRecomendation aiRecomendation) {
                // Busca el registro antes de eliminarlo fisicamente.
AiRecomendation existing = jpaAiRecomendation.findById(aiRecomendation.getRecommendationId()).orElse(null);
if (existing != null) {
    jpaAiRecomendation.delete(existing);
    return 1;
}
// Retorna cero si el registro no existe.
return 0;
    }

    @Override
    public AiRecomendation findByAiRecomendation(int recommendationId) {
        // Busca por identificador y retorna null si no existe.
        return jpaAiRecomendation.findById(recommendationId).orElse(null);
    }

    @Override
    public List<AiRecomendation> findAll() {
        // Retorna todos los registros de la tabla.
        return jpaAiRecomendation.findAll();
    }
}
