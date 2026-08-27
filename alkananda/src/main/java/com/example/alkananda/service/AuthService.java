package com.example.alkananda.service;
import com.example.alkananda.dto.LoginRequest;
import com.example.alkananda.dto.LoginResponse;
import com.example.alkananda.entity.User;
import com.example.alkananda.exception.ResourceNotFoundException;
import com.example.alkananda.repository.userRepository;
import com.example.alkananda.security.JwtService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final userRepository userRepository;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    public AuthService(userRepository userRepository,JwtService jwtService){
        this.jwtService=jwtService;
        this.userRepository=userRepository;
    }

    public LoginResponse login(LoginRequest request){
        User user=userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new ResourceNotFoundException("Invalid user or password")
        );
        boolean passwordMatch=passwordEncoder.matches(request.getPassword(),
                                    user.getPassword());
        if(!passwordMatch){
            throw new ResourceNotFoundException("Invalid email or password");
        }
        String token =jwtService.generateToken(user.getEmail());
        System.out.println("========== NEW TOKEN ==========");
        System.out.println(token);
        return new LoginResponse(
                token,user.getEmail(),user.getRole()
        );
    }
}
