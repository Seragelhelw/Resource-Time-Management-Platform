package com.example.asweprj.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.asweprj.demo.models.Employee;
import com.example.asweprj.demo.models.TimeTracking;

@Repository
public interface TimeTrackingRepository extends JpaRepository<TimeTracking, Long> {
    List<TimeTracking> findByEmployee(Employee employee);
}