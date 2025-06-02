package co.in.sagarkale.globalexceptionhandling.repository;

import co.in.sagarkale.globalexceptionhandling.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    private List<User> users = List.of(
            new User(101, "Sagar", 20, true),
            new User(204, "Bob", 30, false),
            new User(306, "Martin", 40, true)
    );

    public User findById(int userId){
        for (User user: users){
            if(user.getId() == userId){
                return user;
            }
        }
        return null;
    }
}
