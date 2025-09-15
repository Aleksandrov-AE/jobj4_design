package ru.job4j.ood.srp.model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "employees")
public class EmployeesXml {

    @XmlElement(name = "employee")
    public List<EmployeeXml> employees;

    public EmployeesXml() { }

    public EmployeesXml(List<EmployeeXml> employees) {
        this.employees = employees;
    }
}

