package com.xalface.microservices.auth.xAlface_AuthService.services;

import com.xalface.microservices.auth.xAlface_AuthService.clients.UserServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserServiceClient userServiceClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Tenta buscar como admin
        try {
            var admin = userServiceClient.findAdminByUsername(username);
            if (admin != null) {
                return admin;
            }
        } catch (Exception e) {
            // Se não encontrar como admin, continua a busca
        }

        // Tenta buscar como professor
        try {
            var teacher = userServiceClient.findTeacherByUsername(username);
            if (teacher != null) {
                return teacher;
            }
        } catch (Exception e) {
            // Se não encontrar como professor, lança exceção
        }

        throw new UsernameNotFoundException("Usuário não encontrado");
    }

    public String authenticate(Authentication authentication) {
        return jwtService.generateToken(authentication);
    }
}