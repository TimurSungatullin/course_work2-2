package ru.kpfu.itis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.Role;
import ru.kpfu.itis.repository.RoleRepository;
import ru.kpfu.itis.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.NoSuchElementException;
import java.util.Set;

@Component
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository userAuthorityRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(final String email) {
        try {
            return userRepo.findByEmail(email);
        }
        catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException("User with id " + email + "has not been found.");
        }
    }

    public void registerUser(User user) {
        user.addRole(userAuthorityRepo.findRoleByRole("ROLE_USER"));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setPasswordRepeat(user.getPassword());
        userRepo.save(user);
    }

    public User updateFullNameAndAuthorities(Long id, Set<Role> authorities) {
        try {
            User user = userRepo.findById(id).get();
            user.setPasswordRepeat(user.getPassword());
            user.setRoles(authorities);
            return user;
        } catch (NoSuchElementException ex) {
            throw new EntityNotFoundException("User with id " + id + "has not been found.");
        }
    }

}
