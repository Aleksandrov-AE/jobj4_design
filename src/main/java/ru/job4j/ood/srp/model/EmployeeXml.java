package ru.job4j.ood.srp.model;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"name", "hired", "fired", "salary"})
public class EmployeeXml {
    private String name;
    private String hired;
    private String fired;
    private double salary;

    public EmployeeXml() {
    }
    public EmployeeXml(String name, String hired, String fired, double salary) {
        this.name = name;
        this.hired = hired;
        this.fired = fired;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHired() {
        return hired;
    }

    public void setHired(String hired) {
        this.hired = hired;
    }

    public String getFired() {
        return fired;
    }

    public void setFired(String fired) {
        this.fired = fired;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
