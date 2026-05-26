package com.jma.orderapi.controller;

import com.jma.orderapi.config.TokenService;
import com.jma.orderapi.exceptions.BadResourceRequestException;
import com.jma.orderapi.model.User;
import com.jma.orderapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    public record LoginRequest(String username, String password) {}

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequest request) {
        // 1. Busca o usuário pelo userName
        User user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new BadResourceRequestException("Invalid username or password"));

        // 2. Valida se o usuário está ativo
        if (!user.getActive()) {
            throw new BadResourceRequestException("User account is deactivated");
        }

        // 3. Compara a senha digitada com o hash criptografado do banco
        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new BadResourceRequestException("Invalid username or password");
        }

        // 4. Se tudo estiver certo, gera o Token JWT
        String token = tokenService.generateToken(user);

        // Retorna o token em formato JSON para o front-end
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return response;
    }
}