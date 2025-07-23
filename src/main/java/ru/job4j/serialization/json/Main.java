package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.serialization.Address;
import ru.job4j.serialization.Person;

public class Main {
    public static void main(String[] args) {
        Person person = new Person(true, 38, "Anton",
                new Address("rus", "spb", "Gusarskay", 194567),
                new String[]{"photo", "video"});
        Gson gson = new GsonBuilder().create();
        String jsonPerson = gson.toJson(person);
        System.out.println(jsonPerson);
        Person personReturn = gson.fromJson(jsonPerson, Person.class);
        System.out.println(person.equals(personReturn));
    }


}
