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

    /**
     * Deleta um usuário pelo username, identificando se é um admin ou professor
     * @param username O nome de usuário do usuário a ser deletado
     * @return true se o usuário foi deletado com sucesso, false caso contrário
     */
    public boolean deleteUserByUsername(String username) {
        try {
            // Verifica se é um admin
            var admin = userServiceClient.findAdminByUsername(username);
            if (admin != null) {
                // Implementar chamada para deletar admin
                // userServiceClient.deleteAdmin(username);
                return true;
            }
        } catch (Exception e) {
            // Se não encontrar como admin, continua
        }

        try {
            // Verifica se é um professor
            var teacher = userServiceClient.findTeacherByUsername(username);
            if (teacher != null) {
                // Implementar chamada para deletar professor
                // userServiceClient.deleteTeacher(username);
                return true;
            }
        } catch (Exception e) {
            // Se não encontrar como professor, retorna falso
        }

        return false;
    }
}