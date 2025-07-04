package com.example.asweprj.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.asweprj.demo.models.WorkLoadBalancer;

@Repository
public interface WorkLoadBalancerRepository extends JpaRepository<WorkLoadBalancer, Long> {
}