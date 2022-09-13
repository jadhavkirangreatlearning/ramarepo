package com.csi.dao;

import com.csi.model.Employee;

import java.util.List;

public interface EmployeeDao {

    public void signUp(Employee employee);

    public boolean signIn(String empEmailId, String empPassword);

    public List<Employee> getAllData();

    public void updateData(int empId, Employee employee);

    public void deleteDataById(int empId);

    public List<Employee> sortDataByEmpAge();

    public List<Employee> sortDataByEmpName();

    public List<Employee> filterDataBySalary(double empSalary);


}
