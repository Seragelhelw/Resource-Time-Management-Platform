package com.example.asweprj.demo.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import com.example.asweprj.demo.models.Employee;
import com.example.asweprj.demo.models.Manager;
import com.example.asweprj.demo.models.User;
import com.example.asweprj.demo.repositories.UserRepository;
import com.example.asweprj.demo.repositories.EmployeeRepository;
import com.example.asweprj.demo.repositories.ManagerRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;
    private final ManagerRepository managerRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthController(UserRepository userRepository,
                          EmployeeRepository employeeRepository,
                          ManagerRepository managerRepository) {
        this.userRepository = userRepository;
        this.employeeRepository = employeeRepository;
        this.managerRepository = managerRepository;
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") User user, Model model) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            model.addAttribute("error", "Email already exists.");
            return "register";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        switch (user.getRole().toUpperCase()) {
            case "EMPLOYEE" -> {
                Employee emp = new Employee();
                emp.setName(user.getName());
                emp.setEmail(user.getEmail());
                emp.setPassword(user.getPassword());
                emp.setRole("EMPLOYEE");
                emp.setWorkload(0);
                employeeRepository.save(emp);
            }
            case "MANAGER" -> {
                Manager mgr = new Manager();
                mgr.setName(user.getName());
                mgr.setEmail(user.getEmail());
                mgr.setPassword(user.getPassword());
                mgr.setRole("MANAGER");
                managerRepository.save(mgr);
            }
            default -> {
                model.addAttribute("error", "Invalid role.");
                return "register";
            }
        }

        return "redirect:/auth/login";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User formUser, HttpSession session, Model model) {
        Optional<User> optionalUser = userRepository.findByEmail(formUser.getEmail());

        if (optionalUser.isEmpty() || !passwordEncoder.matches(formUser.getPassword(), optionalUser.get().getPassword())) {
            model.addAttribute("error", "Invalid credentials.");
            return "login";
        }

        User user = optionalUser.get();
        session.setAttribute("loggedInUser", user);

        // Redirect based on role
        return switch (user.getRole().toUpperCase()) {
            case "EMPLOYEE" -> "redirect:/employee/dashboard";
            case "MANAGER" -> "redirect:/manager/dashboard";
            default -> "redirect:/";
        };
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/auth/login";
    }
}