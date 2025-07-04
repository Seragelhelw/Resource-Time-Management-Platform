package com.example.asweprj.demo.models;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
// import java.util.List;
// import java.util.Set;

@Entity
@Data
@NoArgsConstructor

public class TimeTracking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trackingId;
    private String startDate;
    private String endDate;
    private int duration;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}