package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReportHrTest {
    private Store store;
    private DateTimeParser<Calendar> parser;
    private Calendar fixed;
    private Calendar hired;
    private Calendar fired;
    private Predicate<Employee> filter;
    private List<Employee> sortEmployee;
    private Comparator<Employee> comparator = Comparator.comparingDouble(Employee::getSalary).reversed();


    @BeforeEach
    void setUp() {
        fixed = new GregorianCalendar(2025, Calendar.SEPTEMBER, 11, 10, 0, 0);
        hired = (Calendar) fixed.clone();
        fired = (Calendar) fixed.clone();
        fired.add(Calendar.MONTH, 2);
        store = new MemoryStore();
        parser = new ReportDateTimeParser();
        filter = e -> e.getSalary() > 10_000;
        for (int i = 1; i < 4; i++) {
            store.add(new Employee(
                    "Tops" + i,
                    hired,
                    fired,
                    10_000 * i
            ));
        }
        sortEmployee = store.findBy(filter).stream().sorted(comparator).collect(Collectors.toList());
    }

    @Test
    void generate() {
       ReportHr reportHr = new ReportHr(store);
       String report = reportHr.generate(filter);
       StringBuilder stringBuilder = new StringBuilder();
       String ln = System.lineSeparator();
       stringBuilder.append("Name; Salary;").append(ln);
       for (Employee employee: sortEmployee) {
           stringBuilder.append(employee.getName()).append(" ")
                   .append(employee.getSalary()).append(ln);
       }
       String expected = stringBuilder.toString();
       assertEquals(expected, report);
    }
}