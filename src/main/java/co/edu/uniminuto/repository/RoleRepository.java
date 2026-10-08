package co.edu.uniminuto.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.edu.uniminuto.entity.Role;
import co.edu.uniminuto.jpa.JpaRole;

@Repository
public class RoleRepository implements RoleRepositoryI {

    @Autowired
    JpaRole jpaRole;

    @Override
    public Role insertRole(Role role) {
        return jpaRole.save(role);
    }

    @Override
    public Role updateRole(Role role) {
        return jpaRole.save(role);
    }

    @Override
    public int deleteRole(Role role) {
        if (!jpaRole.save(role).equals(null)) {
            return 1;
        }
        return 0;
    }

    @Override
    public Role findByRole(int roleId) {
        return jpaRole.findById(roleId).orElse(null);
    }

    @Override
    public List<Role> findAll() {
        return jpaRole.findAll();
    }
}