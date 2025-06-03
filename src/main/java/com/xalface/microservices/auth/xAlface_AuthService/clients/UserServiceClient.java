package com.xalface.microservices.auth.xAlface_AuthService.clients;

import com.xalface.microservices.auth.xAlface_AuthService.DTOs.AdminDTO;
import com.xalface.microservices.auth.xAlface_AuthService.DTOs.TeacherDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "xalface_userService", url = "${user.service.url}")
public interface UserServiceClient {

    @GetMapping("/user/teacher/{id}")
    TeacherDTO findTeacherById(@PathVariable Long id);

    @GetMapping("/user/admin/{id}")
    AdminDTO findAdminById(@PathVariable Long id);

    @GetMapping("/user/teacher/{username}")
    TeacherDTO findTeacherByUsername(@PathVariable String username);

    @GetMapping("/user/admin/{username}")
    AdminDTO findAdminByUsername(@PathVariable String username);

    @PostMapping("/user/teacher/create")
    TeacherDTO saveTeacher(@RequestBody TeacherDTO obj);

    @PostMapping("/user/admin/create")
    AdminDTO saveAdmin(@RequestBody AdminDTO obj);

}
