package ru.grishchenko.repositories;

import ru.grishchenko.entity.UserWeb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class UserRepository {

    private final Map<Long, UserWeb> userMap = new ConcurrentHashMap<Long, UserWeb>();

    private final AtomicLong identity = new AtomicLong(0);

    public UserRepository() {
        saveOrUpdate(new UserWeb(null, "John Dow" , "john", "123", "john@mail.com"));
        saveOrUpdate(new UserWeb(null, "Bob Johnson" , "bob", "123", "bob@mail.com"));
        saveOrUpdate(new UserWeb(null, "Dmitri Ivanov" , "dima", "123", "dima@mail.com"));
    }

    public List<UserWeb> findAll() {
        return new ArrayList<UserWeb>(userMap.values());
    }

    public UserWeb findById(Long id) {
        return userMap.get(id);
    }

    public void saveOrUpdate(UserWeb userWeb) {
        if (userWeb.getId() == null) {
            Long id = identity.incrementAndGet();
            userWeb.setId(id);
        }
        userMap.put(userWeb.getId(), userWeb);
    }

    public void deleteById(Long id) {
        userMap.remove(id);
    }

}
