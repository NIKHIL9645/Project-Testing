package com.employee.repo;

import com.employee.entity.Employee;
import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

@Repository
public interface EmpRepository extends JpaRepository<Employee , Long> {



}
