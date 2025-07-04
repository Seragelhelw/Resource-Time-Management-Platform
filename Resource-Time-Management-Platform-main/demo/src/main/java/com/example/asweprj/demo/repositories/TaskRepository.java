package com.example.asweprj.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.asweprj.demo.models.Employee;
import com.example.asweprj.demo.models.Manager;
import com.example.asweprj.demo.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByEmployee(Employee employee);
    List<Task> findByManager(Manager manager);
    
    
}