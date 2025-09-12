package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class AccountingReportTest {

    private Store store;
    private DateTimeParser<Calendar> parser;
    private CurrencyConverter converter;
    private Calendar fixed;
    Calendar hired;
    Calendar fired;


    @BeforeEach
    void setUp() {
        fixed = new GregorianCalendar(2025, Calendar.SEPTEMBER, 11, 10, 0, 0);
        hired = (Calendar) fixed.clone();
        fired = (Calendar) fixed.clone();
        fired.add(Calendar.MONTH, 2);
        store = new MemoryStore();
        parser = new ReportDateTimeParser();
        converter = new InMemoryCurrencyConverter();
        store.add(new Employee(
                "Tops",
                hired,
                fired,
                100_000
        ));
    }

    @Test
    void generate() {
        Report accountingReport = new AccountingReport(store, parser, converter, Currency.USD);
        String report = accountingReport.generate(e -> e.getSalary() > 1_000);

        String ln = System.lineSeparator();
        String expected = new StringBuilder()
                .append("Name; Hired; Fired; Salary;").append(ln)
                .append("Tops").append(" ")
                .append(parser.parse(hired)).append(" ")
                .append(parser.parse(fired)).append(" ")
                .append(converter.convert(Currency.RUB, 100_000, Currency.USD)).append(ln)
                .toString();

        assertEquals(expected, report);
    }
}