
package com.example.asweprj.demo.services;

import com.example.asweprj.demo.models.Employee;
import com.example.asweprj.demo.models.WorkLoadBalancer;
import com.example.asweprj.demo.repositories.EmployeeRepository;
import com.example.asweprj.demo.repositories.WorkLoadBalancerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WorkloadService {

    private final EmployeeRepository employeeRepository;
    private final WorkLoadBalancerRepository workLoadBalancerRepository;

    public WorkloadService(EmployeeRepository employeeRepository, WorkLoadBalancerRepository workLoadBalancerRepository) {
        this.employeeRepository = employeeRepository;
        this.workLoadBalancerRepository = workLoadBalancerRepository;
    }

    // Fetch all employees for workload management
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Update an employee's workload
    @Transactional
    public void updateWorkload(Long employeeId, int workload) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow();
        employee.setWorkload(workload);
        employeeRepository.save(employee);  // Updates the workload of the employee
    }

    // Add new workload entry if needed
    @Transactional
    public void addWorkLoad(WorkLoadBalancer workLoadBalancer) {
        workLoadBalancerRepository.save(workLoadBalancer);
    }
}

