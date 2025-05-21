package co.in.sagarkale.responseentityexceptionhandler.service;

import co.in.sagarkale.responseentityexceptionhandler.entity.User;
import co.in.sagarkale.responseentityexceptionhandler.exceptions.UserInActiveException;
import co.in.sagarkale.responseentityexceptionhandler.exceptions.UserNotFoundException;
import co.in.sagarkale.responseentityexceptionhandler.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserDetails(int userId){
        User user = userRepository.findById(userId);

        if(user == null){
            throw new UserNotFoundException("User with provided id does not exist");
        }else if(!user.isActive()){
            throw new UserInActiveException("User id inactive. Please contact admin to activate the user");
        }

        return user;
    }
}
