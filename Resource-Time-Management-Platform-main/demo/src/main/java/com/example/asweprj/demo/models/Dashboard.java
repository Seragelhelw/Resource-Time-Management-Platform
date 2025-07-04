package com.example.asweprj.demo.models;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
// import java.util.List;
// import java.util.Set;
@Entity
@Data
@NoArgsConstructor
public class Dashboard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dashboardId;
    private int totalTasks;
    private int completedTasks;
    private int timeSpent;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}