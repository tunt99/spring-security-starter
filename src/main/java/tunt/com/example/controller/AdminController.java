package tunt.com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    @GetMapping("/vip")
    public String zoneVip() {
        return "Welcome to the VIP zone!";
    }

    @GetMapping("/normal")
    public String zoneNormal() {
        return "Welcome to the Normal zone!";
    }
}
