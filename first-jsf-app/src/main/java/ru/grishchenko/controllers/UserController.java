package ru.grishchenko.controllers;

import ru.grishchenko.entity.User;
import ru.grishchenko.repositories.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@ApplicationScoped
public class UserController {

    @Inject
    private UserRepository userRepository;

    private User user;

    private List<User> users;

    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        users = userRepository.findAll();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getUsers() {
        return users;
    }

    public String createUser() {
        this.user = new User();
        return "/user_edit_form.xhtml?faces-redirect=true";
    }

    public String editUser(User user) {
        this.user = user;
        return "/user_edit_form.xhtml?faces-redirect=true";
    }

    public void deleteUser(User user) {
        userRepository.deleteById(user.getId());
    }

    public String saveUser() {
        userRepository.saveOrUpdate(user);
        return "/users.xhtml?faces-redirect=true";
    }
}
