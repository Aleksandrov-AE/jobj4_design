package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.function.Predicate;

class DevCsvReportTest {
    private Store store;
    private DateTimeParser<Calendar> dateTimeParser;
    private Calendar fixed;
    private Calendar hired;
    private Calendar fired;
    private Predicate<Employee> filter;


    @BeforeEach
    void setUp() {
        fixed = new GregorianCalendar(2025, Calendar.SEPTEMBER, 11, 10, 0, 0);
        hired = (Calendar) fixed.clone();
        fired = (Calendar) fixed.clone();
        fired.add(Calendar.MONTH, 2);
        store = new MemoryStore();
        dateTimeParser = new ReportDateTimeParser();
        filter = e -> e.getSalary() > 10_000;
        for (int i = 1; i < 4; i++) {
            store.add(new Employee(
                    "Tops" + i,
                    hired,
                    fired,
                    10_000 * i
            ));
        }
    }


    @Test
    void generate() {
        DevCsvReport devCsvReport = new DevCsvReport(store, dateTimeParser);
        String report = devCsvReport.generate(filter);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Name,Hired,Fired,Salary")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            stringBuilder.append(employee.getName()).append(",")
                    .append(dateTimeParser.parse(employee.getHired())).append(",")
                    .append(dateTimeParser.parse(employee.getFired())).append(",")
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
        }
    }
}