package com.example.asweprj.demo.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.asweprj.demo.models.Employee;
import com.example.asweprj.demo.models.MenuItem;
import com.example.asweprj.demo.models.Task;
import com.example.asweprj.demo.repositories.EmployeeRepository;
import com.example.asweprj.demo.repositories.TaskRepository;
import com.example.asweprj.demo.services.MenuService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/employee")
public class EmpDashboardController {

    private final EmployeeRepository employeeRepository;
    private final TaskRepository taskRepository;
    private final MenuService menuService;

    public EmpDashboardController(EmployeeRepository employeeRepository, TaskRepository taskRepository, MenuService menuService) {
        this.employeeRepository = employeeRepository;
        this.taskRepository = taskRepository;
        this.menuService = menuService;
    }

    @GetMapping("/dashboard")
    public String showEmployeeDashboard(Model model, HttpSession session) {
        Employee loggedInEmployee = (Employee) session.getAttribute("loggedInUser");

        if (loggedInEmployee == null) {
            return "redirect:/auth/login";
        }

        List<Task> myTasks = taskRepository.findByEmployee(loggedInEmployee);
        List<MenuItem> menuItems = menuService.getMenuItems();

        model.addAttribute("employee", loggedInEmployee);
        model.addAttribute("myTasks", myTasks);
        model.addAttribute("menuItems", menuItems);

        return "employee_dashboard";
    }
}