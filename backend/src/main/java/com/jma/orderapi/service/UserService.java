package com.jma.orderapi.service;

import com.jma.orderapi.exceptions.BadResourceRequestException;
import com.jma.orderapi.exceptions.NoSuchResourceFoundException;
import com.jma.orderapi.model.User;
import com.jma.orderapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Autowired
    private org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchResourceFoundException("User not found with id: " + id));
    }

    @Transactional
    public User create(User user) {
        // Regra de negócio: Valida se o username já existe no banco
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new BadResourceRequestException("username '" + user.getUsername() + "' is already taken.");
        }

        // Criptografa a senha antes de salvar no banco
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Garante que o usuário novo comece ativo
        user.setActive(true);

        return userRepository.save(user);
    }

    @Transactional
    public User update(Long id, User userDetails) {
        User user = findById(id);

        // Se o username mudou, precisamos validar se o novo já não existe
        if (!user.getUsername().equals(userDetails.getUsername()) &&
                userRepository.existsByUsername(userDetails.getUsername())) {
            throw new BadResourceRequestException("username '" + userDetails.getUsername() + "' is already taken.");
        }

        user.setName(userDetails.getName());
        user.setUsername(userDetails.getUsername());

        // Em um sistema real, a atualização de senha teria um fluxo próprio por segurança
        if (userDetails.getPassword() != null && !userDetails.getPassword().isBlank()) {
            user.setPassword(userDetails.getPassword());
        }

        return userRepository.save(user);
    }

    @Transactional
    public void deleteLogically(Long id) {
        User user = findById(id);
        // Exclusão lógica (Soft Delete) mantendo o histórico intacto
        // user.setActive(false);
        // userRepository.save(user);

        // Se optar por exclusão física por enquanto:
        userRepository.delete(user);
    }
}