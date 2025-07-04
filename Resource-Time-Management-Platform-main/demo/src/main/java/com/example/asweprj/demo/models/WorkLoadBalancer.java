package com.example.asweprj.demo.models;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
// import java.util.List;
// import java.util.Set;
@Entity
@Data
@NoArgsConstructor
@RestController
public class WorkLoadBalancer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long balancerId;
    private int time;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;
}