package co.edu.uniminuto.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import co.edu.uniminuto.entity.Nrc;
import co.edu.uniminuto.services.NrcService;

@RestController
@RequestMapping("/nrcs")
public class NrcController {

    @Autowired
    private NrcService nrcService;

    @GetMapping
    public List<Nrc> getAllNrcs() {
        return nrcService.findAll();
    }

    @GetMapping("/{id}")
    public Nrc getNrcById(@PathVariable int id) {
        return nrcService.findById(id);
    }

    @PostMapping
    public Nrc createNrc(@RequestBody Nrc nrc) {
        return nrcService.save(nrc);
    }

    @PutMapping
    public Nrc updateNrc(@RequestBody Nrc nrc) {
        return nrcService.save(nrc);
    }

    @DeleteMapping
    public int deleteNrc(@RequestBody Nrc nrc) {
        return nrcService.delete(nrc);
    }
}