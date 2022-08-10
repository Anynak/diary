package com.anynak.diary.service;

import com.anynak.diary.dto.UserRequest;
import com.anynak.diary.entity.Role;
import com.anynak.diary.entity.User;
import com.anynak.diary.exceptions.ResourceNotFoundException;
import com.anynak.diary.exceptions.UserAlreadyExistsException;
import com.anynak.diary.mapers.UserMapper;
import com.anynak.diary.repositories.RoleRepository;
import com.anynak.diary.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.anynak.diary.entity.RoleName.ROLE_USER;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers().orElseThrow(() -> new ResourceNotFoundException("no users registered"));
    }

    @Override
    public User registerUser(UserRequest userRequest) {

        Optional<User> user = userRepository.findByEmail(userRequest.getEmail());
        if (user.isPresent()) {
            throw new UserAlreadyExistsException("User with email: " + userRequest.getEmail() + " already registered");
        }
        User newUser = UserMapper.INSTANCE.toUser(userRequest);
        newUser.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        Role role = roleRepository.findRoleByRoleName(ROLE_USER);
        newUser.addRole(role);
        return userRepository.save(newUser);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new ResourceNotFoundException("no such user with id: " + id);
        }
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("no such user with email: " + email));
    }

    @Override
    public void addRole(Role role) {

    }
}
