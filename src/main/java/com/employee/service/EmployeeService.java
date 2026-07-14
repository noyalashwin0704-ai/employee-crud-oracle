package com.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.entity.Employee;
import com.employee.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee addEmployee(Employee employee) {
        validateEmployee(employee);
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(long id, Employee employee) {

        if (!employeeRepository.existsById(id)) {
            return null;
        }

        validateEmployee(employee);
        employee.setId(id);

        return employeeRepository.save(employee);
    }

    public boolean deleteEmployee(long id) {

        if (!employeeRepository.existsById(id)) {
            return false;
        }

        employeeRepository.deleteById(id);
        return true;
    }

    public List<Employee> getEmployeesByDepartment(String departmentName) {
        return employeeRepository.findByDepartment(departmentName);
    }

    private void validateEmployee(Employee employee) {

        if (employee.getName() == null || employee.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Employee name is required");
        }

        if (employee.getEmail() == null || employee.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }

        if (employee.getDepartment() == null || employee.getDepartment().trim().isEmpty()) {
            throw new IllegalArgumentException("Department is required");
        }

        if (employee.getSalary() == null || employee.getSalary().doubleValue() <= 0) {
            throw new IllegalArgumentException("Salary must be greater than zero");
        }

        if (employee.getJoinDate() == null) {
            throw new IllegalArgumentException("Join date is required");
        }
    }
}
