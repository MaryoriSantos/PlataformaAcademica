package co.edu.uniminuto.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import co.edu.uniminuto.entity.Nrc;
import co.edu.uniminuto.jpa.JpaNrc;

@Repository
public class NrcRepository implements NrcRepositoryI {

    @Autowired
    private JpaNrc jpaNrc;

    @Override
    public Nrc insertNrc(Nrc nrc) {
        return jpaNrc.save(nrc);
    }

    @Override
    public Nrc updateNrc(Nrc nrc) {
        return jpaNrc.save(nrc);
    }

    @Override
    public int deleteNrc(Nrc nrc) {
        try {
            jpaNrc.delete(nrc);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public Nrc findByNrc(int nrcId) {
        return jpaNrc.findById(nrcId).orElse(null);
    }

    @Override
    public List<Nrc> findAll() {
        return jpaNrc.findAll();
    }
}