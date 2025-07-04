package com.example.asweprj.demo.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;



@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Employee extends User {
    private int workload;
    
    @OneToMany(mappedBy = "employee")
    private List<TimeTracking> timeTrackings;
    
    @OneToOne(mappedBy = "employee")
    private Dashboard dashboard;


    public void setWorkload( int workload) {
        this.workload = workload;
    }
    
}
