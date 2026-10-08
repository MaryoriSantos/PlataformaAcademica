package co.edu.uniminuto.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniminuto.entity.Role;
import co.edu.uniminuto.repository.RoleRepository;

@Service
public class RoleService implements RoleServiceI {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role insertRole(Role role) {
        return roleRepository.insertRole(role);
    }

    @Override
    public Role updateRole(Role role) {
        if (roleRepository.findByRole(role.getId()) != null) {
            return roleRepository.updateRole(role);
        }
        return null;
    }

    @Override
    public int deleteRole(Role role) {
        if (roleRepository.findByRole(role.getId()) != null) {
            roleRepository.deleteRole(role);
            return 1;
        }
        return 0;
    }

    @Override
    public Role findByRole(int roleId) {
        return roleRepository.findByRole(roleId);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}