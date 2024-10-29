package model.controller;

import model.dto.JwtResponse;
import model.dto.LoginRequest;
import model.dto.SignupRequest;
import model.entity.Person;
import model.mapper.RoleMapper;
import model.repository.PersonRepository;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/auth")
public class AuthenticationController {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private PersonRepository userRepository;

    public AuthenticationController(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

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
        return ResponseEntity.ok(new JwtResponse(jwtToken, "Bearer", userDetails.getUsername(), roles));
    }
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest sRequest){
        Person user = new Person(null,"",sRequest.username(),sRequest.email(), passwordEncoder.encode(sRequest.password()),null,null,roleMapper.toRoles(sRequest.roles()));
        Person newUser = userRepository.save(user);
        return ResponseEntity.ok(newUser);
    }
}