package com.anynak.diary.service;

import com.anynak.diary.dto.UserRequest;
import com.anynak.diary.entity.Role;
import com.anynak.diary.entity.User;
import com.anynak.diary.exceptions.UserAlreadyExistsException;
import com.anynak.diary.mapers.UserMapper;
import com.anynak.diary.repositories.RoleRepository;
import com.anynak.diary.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.anynak.diary.RoleName.ROLE_USER;


@Service
public class UserServiceImpl implements UserService{

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public User registerUser(UserRequest userRequest) {

        User user = userRepository.findByEmail(userRequest.getEmail());
        if(user!=null){
            throw new UserAlreadyExistsException("User with email :"+userRequest.getEmail()+" already registered");

        //throw new UserServiceException(new UserAlreadyExistsException("user with email" + userRequest.getEmail()+" already exists"));
        }
        user = UserMapper.INSTANCE.toUser(userRequest);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        Role role = roleRepository.findByName(ROLE_USER);
        user.addRole(role);
        return userRepository.save(user);
    }
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }
    @Override
    public User getUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            return optionalUser.get();
        }else {
            throw new RuntimeException("no such user with id: "+id);
        }
    }

    @Override
    public User getByEmail(String email) {
        userRepository.findByEmail(email);
        return userRepository.findByEmail(email);
    }

    @Override
    public void addRole(Role role) {

    }
}
