package co.in.sagarkale.mvc.services;

import co.in.sagarkale.mvc.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final List<User> users = new ArrayList<>();

    public List<User> getAllUsers() {
        return users;
    }

    public void addUser(String name) {
        users.add(new User(name));
    }
}

