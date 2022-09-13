package com.csi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue
    @Column(name = "emp_id")
    private int empId;

    @Column(name = "emp_name")
    private String empName;

    @Column(name = "emp_address")
    private String empAddress;

    @Column(name = "emp_age")
    private int empAge;

    @Column(name = "emp_contact_number", unique = true)
    private long empContactNumber;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "emp_salary")
    private double empSalary;

    @Column(name = "emp_dob")
    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    private Date empDOB;

    @Column(name = "emp_email_id", unique = true)
    private String empEmailId;

    @Column(name = "emp_password")
    private String empPassword;
}
