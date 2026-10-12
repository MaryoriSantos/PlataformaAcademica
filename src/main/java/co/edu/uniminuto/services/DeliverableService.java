package co.edu.uniminuto.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniminuto.entity.Deliverable;
import co.edu.uniminuto.repository.DeliverableRepository;

@Service
public class DeliverableService {

    @Autowired
    private DeliverableRepository deliverableRepository;

    public List<Deliverable> findAll() {
        return deliverableRepository.findAll();
    }

    public Deliverable findById(int id) {
        return deliverableRepository.findByDeliverable(id);
    }

    public Deliverable save(Deliverable deliverable) {
        return deliverableRepository.insertDeliverable(deliverable);
    }

    public int delete(Deliverable deliverable) {
        return deliverableRepository.deleteDeliverable(deliverable);
    }
}