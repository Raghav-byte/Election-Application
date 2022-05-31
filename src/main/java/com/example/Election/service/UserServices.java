package com.example.Election.service;

import com.example.Election.Repository.UserRepository;
import com.example.Election.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    //CREATING User
    public User createUser(User User) {
        return userRepository.save(User);
    }

    //SHOWS ALL User
    public List<User> showAllUser() {
        return userRepository.findAll();
    }

    //SHOWS ONE User
    public Optional<User> showOneUser(UUID UserId) {
        return userRepository.findById(UserId);
    }

    //DELETES User
    public String deleteUser(UUID UserId) {
        userRepository.deleteById(UserId);
        return "User Deleted";
    }

    //UPDATES User
    public User updateUser(User user) {
        AtomicReference<User> userAtomicReference = new AtomicReference<>();
        Optional<User> existingUser = userRepository.findById(user.getUserId());
        existingUser.ifPresentOrElse(u->{
            u.setUserName(user.getUserName() != null ? user.getUserName() : u.getUserName());
            userAtomicReference.set(userRepository.save(u));
        },()->{
            try {
                throw new Exception("User Not Found");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return userAtomicReference.get();
    }


}
