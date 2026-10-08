package co.edu.uniminuto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uniminuto.entity.Role;
import co.edu.uniminuto.services.RoleServiceI;

@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    RoleServiceI roleService;

    @GetMapping
    public List<Role> getAllRoles() {
        return roleService.findAll();
    }

    @GetMapping("/id")
    public Role getByRole(@RequestParam int id) {
        return roleService.findByRole(id);
    }

    @PostMapping
    public Role postRole(@RequestBody Role role) {
        return roleService.insertRole(role);
    }

    @PutMapping
    public Role putRole(@RequestBody Role role) {
        return roleService.updateRole(role);
    }
}