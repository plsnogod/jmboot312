package com.plsnogod.jmboot.service;

import com.plsnogod.jmboot.model.Role;
import com.plsnogod.jmboot.dao.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findByRole(String nameRoles){
        return roleRepository.findRoleByNameRoles(nameRoles);
    }
    @Override
    public List<Role> list_roles() {
        return roleRepository.findAll();
    }
}
