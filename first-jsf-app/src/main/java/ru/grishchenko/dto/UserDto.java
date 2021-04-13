package ru.grishchenko.dto;

import ru.grishchenko.entity.User;

import java.util.HashSet;
import java.util.Set;

public class UserDto {

    private Long id;

    private String alias;

    private String username;

    private String password;

    private String email;

    private Set<RoleDto> roles;

    public UserDto(){}

    public UserDto(User user) {
        this.id = user.getId();
        this.alias = user.getAlias();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.roles = new HashSet<>();
        user.getRoles().forEach(r -> this.roles.add(new RoleDto(r)));
//        System.out.println(this);
//        roles.addAll(user.getRoles());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDto> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "id: " + this.id + "; name: " + username + "; " + roles.toString();
    }
}
