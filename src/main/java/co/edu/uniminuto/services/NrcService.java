package co.edu.uniminuto.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniminuto.entity.Nrc;
import co.edu.uniminuto.repository.NrcRepository;

@Service
public class NrcService implements NrcServiceI {

    @Autowired
    private NrcRepository nrcRepository;

    @Override
    public List<Nrc> findAll() {
        return nrcRepository.findAll();
    }

    @Override
    public Nrc findById(int id) {
        return nrcRepository.findByNrc(id);
    }

    @Override
    public Nrc save(Nrc nrc) {
        return nrcRepository.insertNrc(nrc);
    }

    @Override
    public int delete(Nrc nrc) {
        return nrcRepository.deleteNrc(nrc);
    }
}