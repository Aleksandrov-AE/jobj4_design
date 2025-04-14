package ru.job4j.serialization;

import java.util.Arrays;
import java.util.Objects;

public class Person {
        private final boolean isAlive;
        private int age;
        private String name;
        private Address address;
        private String[] hobbies;

        public Person(boolean isAlive, int age, String name, Address address, String[] hobbies) {
            this.isAlive = isAlive;
            this.age = age;
            this.name = name;
            this.address = address;
            this.hobbies = hobbies;
        }

        @Override
        public String toString() {
            return "Person{"
                    + "isActive=" + isAlive
                    + ", age=" + age
                    + ", name='" + name + '\''
                    + ", address=" + address
                    + ", hobbies=" + String.join(", ", hobbies)
                    + '}';
        }

    public boolean isAlive() {
        return isAlive;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public String[] getHobbies() {
        return hobbies;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        return isAlive == person.isAlive && age == person.age && Objects.equals(name, person.name) && Objects.equals(address, person.address) && Objects.deepEquals(hobbies, person.hobbies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isAlive, age, name, address, Arrays.hashCode(hobbies));
    }
}
