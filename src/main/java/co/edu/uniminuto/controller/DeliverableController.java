package co.edu.uniminuto.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import co.edu.uniminuto.entity.Deliverable;
import co.edu.uniminuto.services.DeliverableService;

@RestController
@RequestMapping("/deliverables")
public class DeliverableController {

    @Autowired
    private DeliverableService deliverableService;

    @GetMapping
    public List<Deliverable> getAllDeliverables() {
        return deliverableService.findAll();
    }

    @GetMapping("/{id}")
    public Deliverable getDeliverableById(@PathVariable int id) {
        return deliverableService.findById(id);
    }

    @PostMapping
    public Deliverable createDeliverable(@RequestBody Deliverable deliverable) {
        return deliverableService.save(deliverable);
    }

    @PutMapping
    public Deliverable updateDeliverable(@RequestBody Deliverable deliverable) {
        return deliverableService.save(deliverable);
    }

    @DeleteMapping
    public int deleteDeliverable(@RequestBody Deliverable deliverable) {
        return deliverableService.delete(deliverable);
    }
}