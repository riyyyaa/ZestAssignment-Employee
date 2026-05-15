package com.zest.employee_management.service;

import com.zest.employee_management.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface EmployeeService {

    Employee saveEmployee(Employee employee);

    Page<Employee> getAllEmployees(Pageable pageable);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long id);

    Employee updateEmployee(Long id, Employee employee);

    void deleteEmployee(Long id);
}