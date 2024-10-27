package com.example.mercearia.services;

import br.com.cafe.security.enums.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cafe.security.entities.Role;
import br.com.cafe.security.repositories.RoleRepository;

import java.util.List;


@Service
public class RoleService {
	@Autowired
	RoleRepository roleRepository;
    private RoleEnum name;

    public Role save(Role role) {
		return roleRepository.save(role);
	}

}
