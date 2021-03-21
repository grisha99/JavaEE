package ru.grishchenko.controllers;

import ru.grishchenko.dto.RoleDto;
import ru.grishchenko.dto.UserDto;
import ru.grishchenko.services.RoleService;
import ru.grishchenko.services.UserService;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import java.util.List;

@Named
@ApplicationScoped
public class RoleController {

    @EJB
    private RoleService roleService;

    private RoleDto roleDto;

    private List<RoleDto> roles;

    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        roles = roleService.getAllRoles();
//        System.out.println("preloadData UserController");
    }

    public RoleDto getRoleDto() {
        return roleDto;
    }

    public void setRoleDto(RoleDto roleDto) {
        this.roleDto = roleDto;
    }

    public List<RoleDto> getRoles() {
        if (roles == null) {
            roles = roleService.getAllRoles();
        }
        return roles;
    }

//    public String createRole() {
//        this.roleDto = new RoleDto();
//        return "/role_edit_form.xhtml?faces-redirect=true";
//    }
//
//    public String editUser(RoleDto roleDto) {
//        this.roleDto = roleDto;
//        return "/role_edit_form.xhtml?faces-redirect=true";
//    }

//    public void deleteRole(RoleDto roleDto) {
//        roleService.deleteUserById(userDto.getId());
//    }
//
//    public String saveRole() {
//        rolerService.saveOrUpdate(roleDto);
//        return "/roles.xhtml?faces-redirect=true";
//    }
}
