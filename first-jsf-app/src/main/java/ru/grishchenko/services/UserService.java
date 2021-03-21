package ru.grishchenko.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.grishchenko.dto.UserDto;
import ru.grishchenko.entity.User;
import ru.grishchenko.repositories.UserRepository;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@Local
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    @EJB
    private UserRepository userRepository;

    @TransactionAttribute
    public void saveOrUpdate(UserDto userDto) {
        User savedUser = userRepository.saveOrUpdate(new User(userDto));
        userDto.setId(savedUser.getId());
    }

    @TransactionAttribute
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public UserDto getUserById(Long id) {
        return new UserDto(userRepository.findById(id));
    }

    public boolean existUserById(Long id) {
        return userRepository.existsById(id);
    }

    public List<UserDto> getAllUsers() {
        List<UserDto> tmp = userRepository.findAll().stream().map(UserDto::new).collect(Collectors.toList());
        return tmp;
    }

    public UserDto getUserDtoByUsername(String username) {
        return new UserDto(userRepository.getUserByUsername(username));
    }

    public boolean isAdmin(String username) {
        return getUserDtoByUsername(username).
                getRoles().
                stream().
                filter(roles -> "ADMIN".equals(roles.getName())).findFirst().orElse(null) != null;
    }

}
