package ru.job4j.ood.srp.report;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class ReportJson implements Report {
    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;
    private final Gson gson;
    private final Gson prettyGson;

    public ReportJson(Store store, DateTimeParser<Calendar> dateTimeParser, Gson gson) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        this.gson = gson;
        this.prettyGson = gson.newBuilder()
                .setPrettyPrinting()
                .create();
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> employees = store.findBy(filter);

        JsonArray array = new JsonArray();
        for (Employee e : employees) {
            JsonObject obj = new JsonObject();
            obj.addProperty("name", e.getName());
            // Форматируем даты через переданный парсер
            obj.addProperty("hired", dateTimeParser.parse(e.getHired()));
            obj.addProperty("fired", dateTimeParser.parse(e.getFired()));
            obj.addProperty("salary", e.getSalary());
            array.add(obj);
        }
        return prettyGson.toJson(array);
    }
}
