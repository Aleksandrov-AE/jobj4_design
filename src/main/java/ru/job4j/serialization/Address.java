package ru.job4j.serialization;

import java.util.Objects;

public class Address {
    private String country;
    private String city;
    private String street;
    private int index;

    public Address(String country, String city, String street, int index) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.index = index;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Address address = (Address) o;
        return index == address.index && Objects.equals(country, address.country) && Objects.equals(city, address.city) && Objects.equals(street, address.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, city, street, index);
    }
}
