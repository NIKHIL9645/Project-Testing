package com.employee.service;

import com.employee.entity.Employee;
import org.hibernate.query.Page;

import java.awt.print.Pageable;
import java.util.List;

public interface EmpService {


    Employee saveEmp(Employee employee);

    List<Employee> getAllEmp();

    List<Employee> getAll();

}
