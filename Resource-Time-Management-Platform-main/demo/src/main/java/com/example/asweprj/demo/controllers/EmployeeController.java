package com.example.asweprj.demo.controllers;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.asweprj.demo.models.Employee;
import com.example.asweprj.demo.models.Task;
import com.example.asweprj.demo.models.TimeTracking;
import com.example.asweprj.demo.repositories.EmployeeRepository;
import com.example.asweprj.demo.repositories.TaskRepository;
import com.example.asweprj.demo.repositories.TimeTrackingRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    private final TaskRepository taskRepository;
    private final TimeTrackingRepository timeTrackingRepository;
    private final EmployeeRepository employeeRepository;

    public EmployeeController(TaskRepository taskRepository, TimeTrackingRepository timeTrackingRepository, EmployeeRepository employeeRepository) {
        this.taskRepository = taskRepository;
        this.timeTrackingRepository = timeTrackingRepository;
        this.employeeRepository = employeeRepository;
        
    }

    @GetMapping("/tasks")
    public String getTasks(Model model, HttpSession session) {
        Employee loggedInEmployee = (Employee) session.getAttribute("loggedInUser");

        if (loggedInEmployee == null) {
            return "redirect:/auth/login"; 
        }
        List<Task> tasks = taskRepository.findByEmployee(loggedInEmployee);
        
        model.addAttribute("tasks", tasks);
        return "time-tracking-tasks";
    }

    @PostMapping("/tasks/start/{taskId}")
    public String startTimer(@PathVariable Long taskId, HttpSession session) {
        
        Employee loggedInEmployee = (Employee) session.getAttribute("loggedInUser");

        if (loggedInEmployee == null) {
            return "redirect:/auth/login";  
        }
        TimeTracking timeTracking = new TimeTracking();
        timeTracking.setEmployee(loggedInEmployee);
        timeTracking.setTask(taskRepository.findById(taskId).orElse(null));
        timeTracking.setStartDate(LocalDateTime.now().toString());  
        timeTrackingRepository.save(timeTracking);

        Task task = timeTracking.getTask();
        task.setStatus("IN PROGRESS");
        taskRepository.save(task);
        
        return "redirect:/employee/tasks";  
    }

    @PostMapping("/tasks/end/{taskId}")
    public String endTimer(@PathVariable Long taskId, HttpSession session) {
        
        Employee loggedInEmployee = (Employee) session.getAttribute("loggedInUser");

        
        TimeTracking timeTracking = timeTrackingRepository.findByEmployee(loggedInEmployee)
                .stream()
                .filter(tt -> tt.getTask().getTaskId().equals(taskId) && tt.getEndDate() == null)
                .findFirst()
                .orElse(null);

        if (timeTracking != null) {
            
            timeTracking.setEndDate(LocalDateTime.now().toString());
            
            timeTracking.setDuration(calculateDuration(timeTracking.getStartDate(), timeTracking.getEndDate()));
            timeTrackingRepository.save(timeTracking);
        }

        Task task = timeTracking.getTask();
            task.setStatus("DONE");
            taskRepository.save(task);
        
        return "redirect:/employee/tasks";
    }

    private int calculateDuration(String startDate, String endDate) {
        
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

        
        LocalDateTime start = LocalDateTime.parse(startDate, formatter);
        LocalDateTime end = LocalDateTime.parse(endDate, formatter);

        
        Duration duration = Duration.between(start, end);

        
        return (int) duration.toMinutes();
    }
}
