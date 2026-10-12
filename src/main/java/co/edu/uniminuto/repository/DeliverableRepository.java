package co.edu.uniminuto.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import co.edu.uniminuto.entity.Deliverable;
import co.edu.uniminuto.jpa.JpaDeliverable;

@Repository
public class DeliverableRepository implements DeliverableRepositoryI {

    @Autowired
    private JpaDeliverable jpaDeliverable;

    @Override
    public Deliverable insertDeliverable(Deliverable deliverable) {
        return jpaDeliverable.save(deliverable);
    }

    @Override
    public Deliverable updateDeliverable(Deliverable deliverable) {
        return jpaDeliverable.save(deliverable);
    }

    @Override
    public int deleteDeliverable(Deliverable deliverable) {
        try {
            jpaDeliverable.delete(deliverable);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public Deliverable findByDeliverable(int deliverableId) {
        return jpaDeliverable.findById(deliverableId).orElse(null);
    }

    @Override
    public List<Deliverable> findAll() {
        return jpaDeliverable.findAll();
    }
}