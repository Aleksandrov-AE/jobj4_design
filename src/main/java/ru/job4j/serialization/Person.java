package ru.job4j.serialization;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.annotation.*;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Objects;

@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {
        @XmlAttribute
        private boolean isAlive;
        @XmlAttribute
        private int age;
        private String name;
        private Address address;
        private String[] hobbies;

    public Person() {
    }

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

    public static void main(String[] args) throws JAXBException {

        final Person person = new Person(true, 40, "Mike",
                new Address("rus", "Spb", "Puskina", 193144),
                new String[]{"golf", "hunt"});

        JAXBContext context = JAXBContext.newInstance(Person.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(person, writer);
            String result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
