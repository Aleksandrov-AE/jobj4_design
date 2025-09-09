package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@Disabled("реализация Generator ещё не готова")
class GeneratorTest {

    @Test
    void whenAllkeyPresent() {
        String in = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> map = new HashMap<>();
        map.put("name", "John");
        map.put("subject", "Doe");
        Generator generator = new TemplateGenerator();
        String out = generator.produce(in, map);
        assertEquals(out, in);
    }

    @Test
    void whenNotAllkeyPresent() {
        String in = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> map = new HashMap<>();
        map.put("name", "John");
        Generator generator = new TemplateGenerator();
        String out = generator.produce(in, map);
        assertThrows(RuntimeException.class, () -> generator.produce("Doe", map));
    }

    @Test
    void whenNotAllArgPresent() {
        String in = "I am a ${name}";
        Map<String, String> map = new HashMap<>();
        map.put("name", "John");
        Generator generator = new TemplateGenerator();
        String out = generator.produce(in, map);
        assertThrows(RuntimeException.class, () -> generator.produce("Doe", map));
    }

    @Test
    void whenNoPlaceholders() {
        String in = "I am a";
        Map<String, String> map = new HashMap<>();
        map.put("name", "John");
        Generator generator = new TemplateGenerator();
        String out = generator.produce(in, map);
        assertThrows(RuntimeException.class, () -> generator.produce("Doe", map));
    }
}