package ru.grishchenko.services;

import ru.grishchenko.dto.RoleDto;
import ru.grishchenko.repositories.RoleRepository;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@Local
public class RoleService {

    @EJB
    private RoleRepository roleRepository;

    public List<RoleDto> getAllRoles() {
        return roleRepository.getAllRoles().stream().map(RoleDto::new).collect(Collectors.toList());
    }


}
