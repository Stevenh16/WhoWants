package model.controller;

import model.dto.JwtResponse;
import model.dto.LoginRequest;
import model.dto.SignupRequest;
import model.entity.ERole;
import model.entity.Person;
import model.entity.Role;
import model.repository.PersonRepository;
import model.repository.RoleRepository;
import model.security.jwt.JwtUtil;
import model.security.service.PersonDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthenticationController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private PersonRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.username(),
                        loginRequest.password())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken= jwtUtil.generateJwtToken(authentication);
        PersonDetailsImpl userDetails = (PersonDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(jwtToken, "Bearer ", userDetails.getUsername(), roles, userDetails.getId()));
    }
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest sRequest){
        Role defaultRole = roleRepository.findByRole(ERole.ROLE_USER).get();
        Person user = new Person(null, sRequest.name(),sRequest.username(), sRequest.email(), passwordEncoder.encode(sRequest.password()),null,null,null,new HashSet<>(Set.of(defaultRole)));
        Person newUser = userRepository.save(user);
        return ResponseEntity.ok(newUser);
    }
}
