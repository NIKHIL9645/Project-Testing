package com.employee.service;

import com.employee.entity.Employee;
import com.employee.repo.EmpRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {


    @Autowired
    private EmpRepository empRepository;

    @Override
    public Employee saveEmp(Employee employee) {

        Employee save = empRepository.save(employee);


        return save;
    }


    @Override
    public List<Employee> getAllEmp() {
        return empRepository.findAll();
    }


    @Override
    public List<Employee> getAll() {
        return empRepository.findAll();
    }


    //this 2 methods for pagination
    public Page<Employee> getEmployees(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return empRepository.findAll(pageable);
    }

    public List<Employee> getEmployeesForPage(int page, int size) {
        Page<Employee> employeePage = getEmployees(page, size);
        return employeePage.getContent();
    }


}
