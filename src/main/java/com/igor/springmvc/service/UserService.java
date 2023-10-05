package com.igor.springmvc.service;

import com.igor.springmvc.DTO.AuthRequest;
import com.igor.springmvc.model.User;
import com.igor.springmvc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<User> readAll() {
        return (List<User>) userRepository.findAll();
    }

    public boolean create(AuthRequest authRequest) {
        if(userRepository.findByUsername(authRequest.username()).isPresent())
            return false;
        User user = new User();
        user.setUsername(authRequest.username());
        user.setPassword(bCryptPasswordEncoder.encode(authRequest.password()));
        userRepository.save(user);
        return true;
    }
    public void delete(int id) {
        userRepository.deleteById(id);
    }

    public void checkName(String username) throws Exception {
        userRepository.findByUsername(username).orElseThrow(() -> new Exception("Пользователь не найден"));
    }
    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).map(user -> new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles()
        )).orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }
}
