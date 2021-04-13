package ru.grishchenko.controllers;

import ru.grishchenko.dto.UserDto;
import ru.grishchenko.services.UserService;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import java.util.List;

@Named
@ApplicationScoped
public class UserController {

    @EJB
    private UserService userService;

    private UserDto userDto;

    private List<UserDto> users;

    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        users = userService.getAllUsers();
//        System.out.println("preloadData UserController");
    }

    public UserDto getUser() {
        return userDto;
    }

    public void setUser(UserDto userDto) {
        this.userDto = userDto;
    }

    public List<UserDto> getUsers() {
        return users;
    }

    public String createUser() {
        this.userDto = new UserDto();
        return "/admin/user_edit_form.xhtml";
    }

    public String editUser(UserDto userDto) {
        this.userDto = userDto;
        return "/admin/user_edit_form.xhtml";
    }

    public void deleteUser(UserDto userDto) {
        userService.deleteUserById(userDto.getId());
    }

    public String saveUser() {
        userService.saveOrUpdate(userDto);
        return "/admin/users.xhtml";
    }

    public boolean isAdmin(String username) {
        if (username.isEmpty()) {
            return false;
        }
        return userService.isAdmin(username);
    }
}
