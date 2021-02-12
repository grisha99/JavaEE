package ru.grishchenko.repositories;

import ru.grishchenko.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class UserRepository {

    private final Map<Long, User> userMap = new ConcurrentHashMap<Long, User>();

    private final AtomicLong identity = new AtomicLong(0);

    public UserRepository() {
        saveOrUpdate(new User(null, "John Dow" , "john", "123", "john@mail.com"));
        saveOrUpdate(new User(null, "Bob Johnson" , "bob", "123", "bob@mail.com"));
        saveOrUpdate(new User(null, "Dmitri Ivanov" , "dima", "123", "dima@mail.com"));
    }

    public List<User> findAll() {
        return new ArrayList<User>(userMap.values());
    }

    public User findById(Long id) {
        return userMap.get(id);
    }

    public void saveOrUpdate(User user) {
        if (user.getId() == null) {
            Long id = identity.incrementAndGet();
            user.setId(id);
        }
        userMap.put(user.getId(), user);
    }

    public void deleteById(Long id) {
        userMap.remove(id);
    }

}
