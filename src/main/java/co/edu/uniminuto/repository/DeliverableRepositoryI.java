package co.edu.uniminuto.repository;

import java.util.List;
import co.edu.uniminuto.entity.Deliverable;

public interface DeliverableRepositoryI {
    Deliverable insertDeliverable(Deliverable deliverable);
    Deliverable updateDeliverable(Deliverable deliverable);
    int deleteDeliverable(Deliverable deliverable);
    Deliverable findByDeliverable(int deliverableId); // O findById según prefieras
    List<Deliverable> findAll();
}