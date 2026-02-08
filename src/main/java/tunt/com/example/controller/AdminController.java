package tunt.com.example.controller;

import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    @RolesAllowed("ADMIN")
    @GetMapping("/vip")
    public String zoneVip() {
        return "Welcome to the VIP zone!";
    }

    @RolesAllowed({"ADMIN", "USER"})
    @GetMapping("/normal")
    public String zoneNormal() {
        return "Welcome to the Normal zone!";
    }

    @GetMapping("/info")
    public Authentication getUserInfo() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
