package ru.job4j.ood.srp.report;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.model.EmployeeXml;
import ru.job4j.ood.srp.model.EmployeesXml;
import ru.job4j.ood.srp.store.Store;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class ReportXml implements Report {
    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;

    public ReportXml(Store store, DateTimeParser<Calendar> dateTimeParser) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> employees = store.findBy(filter);
        List<EmployeeXml> employeesXml = employees.stream()
                .map(e -> new EmployeeXml(e.getName(), dateTimeParser.parse(e.getHired()), dateTimeParser.parse(e.getFired()), e.getSalary()))
                .toList();
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            JAXBContext context = JAXBContext.newInstance(EmployeesXml.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(new EmployeesXml(employeesXml), writer);
            xml = writer.getBuffer().toString();
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        return xml;
    }
}
