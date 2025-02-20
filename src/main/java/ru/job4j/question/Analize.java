package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Map<Integer, String> prevMap = new HashMap<>();
        for (User user: previous) {
            prevMap.put(user.getId(), user.getName());
        }
        int add = 0;
        int  delete = 0;
        int changed = 0;
        for (User user: current) {
            String name = prevMap.remove(user.getId());
            if (name == null) {
                add++;
            } else {
                if (!name.equals(user.getName())) {
                    changed++;
                }
            }
        }
        delete = prevMap.size();
        return new Info(add, changed, delete);
    }

}