package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.job4j.serialization.Address;
import ru.job4j.serialization.Contact;
import ru.job4j.serialization.Person;

import java.util.ArrayList;
import java.util.List;

public class JsonPojo {
    public static void main(String[] args) {
        JSONObject jsonAddress = new JSONObject("{\"city\":\"spb\",\"index\":\"193435\",\"country\":\"rus\",\"street\":\"vvv\"}");
        List<String> list = new ArrayList<>();
        list.add("golf");
        list.add("hunt");
        JSONArray jsonHobbies = new JSONArray(list);


        final Person person = new Person(false, 30, "Anton",
                new Address("rus", "spb", "vvv", 193435), new String[]{"golf", "hunt"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("alive", person.isAlive());
        jsonObject.put("name", person.getName());
        jsonObject.put("age", person.getAge());
        jsonObject.put("address", jsonAddress);
        jsonObject.put("hobbies", jsonHobbies);

        /* Выведем результат в консоль */
        System.out.println(jsonObject.toString());

        /* Преобразуем объект person в json-строку */
        System.out.println(new JSONObject(person).toString());
    }

}
