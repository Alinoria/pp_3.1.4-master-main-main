package ru.chelysheva.btstrap.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.chelysheva.btstrap.models.User;
import ru.chelysheva.btstrap.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping("/userList")

    public ResponseEntity<List<User>> showUserList() {
        List<User> users = userService.findAll(); // Используйте инжектированный userService
        return ResponseEntity.ok(users);
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            request.getSession().invalidate();
        }
        return "redirect:/login";
    }

        @GetMapping("/current")
        public ResponseEntity<User> getCurrentUser(Principal principal) {
            User user = userService.findByUsername(principal.getName());
            return ResponseEntity.ok(user);
        }
    }


