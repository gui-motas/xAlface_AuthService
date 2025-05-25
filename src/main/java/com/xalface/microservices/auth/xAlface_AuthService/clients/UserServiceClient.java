package com.xalface.microservices.auth.xAlface_AuthService.clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "${user.service.url}")
public interface UserServiceClient {

    @GetMapping("/user/teacher/{id}")
    TeacherDTO findTeacherById(@PathVariable Long id);

    @GetMapping("/user/admin/{id}")
    AdminDTO findAdminById(@PathVariable Long id);

    @GetMapping("/user/teacher/{username}")
    TeacherDTO findTeacherByUsername(@PathVariable String username);

    @GetMapping("/user/admin/{username}")
    AdminDTO findAdminByUsername(@PathVariable String username);

}
