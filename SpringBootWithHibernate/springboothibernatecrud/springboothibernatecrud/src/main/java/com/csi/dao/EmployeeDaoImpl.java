package com.csi.dao;

import com.csi.model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeDaoImpl implements EmployeeDao{

    private static SessionFactory factory= new AnnotationConfiguration().configure().buildSessionFactory();

    @Override
    public void signUp(Employee employee) {

        Session session=factory.openSession();

        Transaction transaction=session.beginTransaction();

        session.save(employee);

        transaction.commit();

    }

    @Override
    public boolean signIn(String empEmailId, String empPassword) {

        Session session=factory.openSession();

        boolean flag = false;
        for(Employee employee: getAllData()){
            if(employee.getEmpEmailId().equals(empEmailId) && employee.getEmpPassword().equals(empPassword)){
                flag= true;
            }
        }
        return flag;
    }

    @Override
    public List<Employee> getAllData() {

        Session session=factory.openSession();

        List<Employee> employeeList = session.createQuery("from Employee").list();
        return employeeList;
    }

    @Override
    public void updateData(int empId, Employee employee) {

        Session session=factory.openSession();
        Transaction transaction = session.beginTransaction();

        Employee employee1= (Employee) session.get(Employee.class, empId);

        employee1.setEmpName(employee.getEmpName());
        employee1.setEmpAddress(employee.getEmpAddress());
        employee1.setEmpContactNumber(employee.getEmpContactNumber());
        employee1.setEmpDOB(employee.getEmpDOB());
        employee1.setEmpEmailId(employee.getEmpEmailId());
        employee1.setEmpPassword(employee.getEmpPassword());
        employee1.setActive(employee.isActive());
        employee1.setEmpSalary(employee.getEmpSalary());

        session.update(employee1);
        transaction.commit();
    }

    @Override
    public void deleteDataById(int empId) {

        Session session=factory.openSession();
        Transaction transaction = session.beginTransaction();
        Employee employee1= (Employee) session.get(Employee.class, empId);

        session.delete(employee1);
        transaction.commit();
    }

    @Override
    public List<Employee> sortDataByEmpAge() {
        return getAllData().stream().sorted(Comparator.comparingLong(Employee::getEmpAge)).collect(Collectors.toList());
    }

    @Override
    public List<Employee> sortDataByEmpName() {
        return getAllData().stream().sorted((e1, e2)-> e1.getEmpName().compareTo(e2.getEmpName())).collect(Collectors.toList());
    }

    @Override
    public List<Employee> filterDataBySalary(double empSalary) {
        return getAllData().stream().filter(emp-> emp.getEmpSalary()>=empSalary).collect(Collectors.toList());
    }


}
