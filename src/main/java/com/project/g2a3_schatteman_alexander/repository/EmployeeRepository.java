package com.project.g2a3_schatteman_alexander.repository;

import com.project.g2a3_schatteman_alexander.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
