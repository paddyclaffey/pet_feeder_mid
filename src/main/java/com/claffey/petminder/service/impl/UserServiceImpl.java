package com.claffey.petminder.service.impl;

import com.claffey.petminder.email.EmailService;
import com.claffey.petminder.security.ConfirmationToken;
import com.claffey.petminder.security.RepositoryTokenRepository;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.proc.BadJOSEException;
import com.claffey.petminder.model.entity.RoleEntity;
import com.claffey.petminder.model.entity.User;
import com.claffey.petminder.repository.RoleJpaRepository;
import com.claffey.petminder.repository.UserJpaRepository;
import com.claffey.petminder.service.UserService;
import com.claffey.petminder.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    EmailService emailService;

    private static final String USER_NOT_FOUND_MESSAGE = "User with username %s not found";

    private final UserJpaRepository userJpaRepository;
    private final RepositoryTokenRepository repositoryTokenRepository;

    private final RoleJpaRepository roleJpaRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userJpaRepository.findByUsername(username);
        if(user == null) {
            String message = String.format(USER_NOT_FOUND_MESSAGE, username);
            log.error(message);
            throw new UsernameNotFoundException(message);
        } else {
            log.debug("User found in the database: {}", username);
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            user.getRoles().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            });
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
        }
    }

    public Long findUserIdByUsername(String username) {
        User user = userJpaRepository.findByUsername(username);
        if (user != null) {
            return user.getId();
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    @Override
    public User save(User user) {
        log.info("Saving user {} to the database", user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userJpaRepository.save(user);
    }

    @Override
    public ResponseEntity<?> register(User user) {
        if (userJpaRepository.findByEmailIgnoreCase(user.getEmail()) != null) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userJpaRepository.save(user);

        ConfirmationToken confirmationToken = new ConfirmationToken(null, UUID.randomUUID().toString(), null, user);

        confirmationToken = repositoryTokenRepository.save(confirmationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom("petminder@gmail.com");
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setText("To confirm your account, please click here : "
                +"http://localhost:8085/confirm-account?token="+confirmationToken.getConfirmationToken());
        emailService.sendEmail(mailMessage);

        System.out.println("Confirmation Token: " + confirmationToken.getConfirmationToken());

        return ResponseEntity.ok("Verify email by the link sent on your email address");
    }

    @Override
    public ResponseEntity<?> confirmEmail(String confirmationToken) {
        ConfirmationToken token = repositoryTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null)
        {
            User user = userJpaRepository.findByEmailIgnoreCase(token.getUser().getEmail());
            user.setIsEnabled(true);
            userJpaRepository.save(user);
            return ResponseEntity.ok("Email verified successfully!");
        }
        return ResponseEntity.badRequest().body("Error: Couldn't verify email");
    }

    @Override
    public User addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}", roleName, username);
        User user = userJpaRepository.findByUsername(username);
        RoleEntity roleEntity = roleJpaRepository.findByName(roleName);
        user.getRoles().add(roleEntity);
        return user;
    }

    @Transactional(readOnly = true)
    @Override
    public User findById(Long id) {
        log.info("Retrieving user {}", id);
        Optional<User> userOptional = userJpaRepository.findById(id);

        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            return null;
            // Handle the case when the user is not found
            // e.g., throw an exception, return null, or return a default User object
        }
    }


    @Transactional(readOnly = true)
    @Override
    public User findByUsername(String username) {
        log.info("Retrieving user {}", username);
        return userJpaRepository.findByUsername(username);
    }


    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() {
        log.info("Retrieving all users");
        return userJpaRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Map<String,String> refreshToken(String authorizationHeader, String issuer) throws BadJOSEException,
            ParseException, JOSEException {

        String refreshToken = authorizationHeader.substring("Bearer ".length());
        UsernamePasswordAuthenticationToken authenticationToken = JwtUtil.parseToken(refreshToken);
        String username = authenticationToken.getName();
        User user = findByUsername(username);
        List<String> roles = user.getRoles().stream().map(RoleEntity::getName).collect(Collectors.toList());
        String accessToken = JwtUtil.createAccessToken(username, issuer, roles);
        return Map.of("access_token", accessToken, "refresh_token", refreshToken);
    }

    @Transactional
    @Override
    public User addUser(User user) {
        return userJpaRepository.save(user);
    }

    @Transactional
    @Override
    public ResponseEntity<?> remove(Long userId) {
        userJpaRepository.delete(findById(userId));
        return ResponseEntity.ok("removed user");
    }
}
