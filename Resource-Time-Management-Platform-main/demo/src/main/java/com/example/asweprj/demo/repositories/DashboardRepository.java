package com.example.asweprj.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.asweprj.demo.models.Dashboard;
import com.example.asweprj.demo.models.Employee;

@Repository
public interface DashboardRepository extends JpaRepository<Dashboard, Long> {
    Optional<Dashboard> findByEmployee(Employee employee);
}