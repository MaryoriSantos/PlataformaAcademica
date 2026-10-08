package co.edu.uniminuto.repository;

import java.util.List;

import co.edu.uniminuto.entity.Role;

public interface RoleRepositoryI {

    Role insertRole(Role role);

    Role updateRole(Role role);

    int deleteRole(Role role);

    Role findByRole(int roleId);

    List<Role> findAll();
}