package co.edu.uniminuto.services;

import java.util.List;
import co.edu.uniminuto.entity.Deliverable;

public interface DeliverableServiceI {
    Deliverable insertDeliverable(Deliverable deliverable);
    Deliverable updateDeliverable(Deliverable deliverable);
    int deleteDeliverable(Deliverable deliverable);
    Deliverable findByDeliverable(int deliverableId);
    List<Deliverable> findAll();
}