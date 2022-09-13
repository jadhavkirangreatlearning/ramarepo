package com.csi.dao;

import com.csi.model.Employee;
import com.csi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class EmployeeDaoImpl {

    @Autowired
    EmployeeRepository employeeRepositoryImpl;

    public Employee signUp(Employee employee){
        return employeeRepositoryImpl.save(employee);
    }

    public List<Employee> getAllData(){
        return employeeRepositoryImpl.findAll();
    }

    public List<Employee> getDataByEmpName(String empName){
        return employeeRepositoryImpl.findByEmpName(empName);
    }

    public boolean signIn(String empEmailId, String empPassword){
        boolean status= false;

        for(Employee employee: employeeRepositoryImpl.findAll()){
            if(employee.getEmpEmailId().equals(empEmailId) && employee.getEmpPassword().equals(empPassword)){
                status=true;
            }
        }
        return status;
    }

    public Employee updateData(Employee employee){
        return employeeRepositoryImpl.save(employee);
    }

    public void deleteDataById(int empId){
        employeeRepositoryImpl.deleteById(empId);
    }


    public List<Employee> sortByAge(){
        return employeeRepositoryImpl.findAll().stream().sorted(Comparator.comparingLong(Employee::getEmpAge)).collect(Collectors.toList());
    }

    public List<Employee> sortByName(){
        return employeeRepositoryImpl.findAll().stream().sorted((e1, e2)-> e1.getEmpName().compareTo(e2.getEmpName())).collect(Collectors.toList());

    }

    public List<Employee> filterDataBySalary(double empSalary){
        return employeeRepositoryImpl.findAll().stream().filter(emp-> emp.getEmpSalary()>=empSalary).collect(Collectors.toList());
    }

    public Optional<Employee> getDataById(int empId){
        return employeeRepositoryImpl.findById(empId);
    }
}
