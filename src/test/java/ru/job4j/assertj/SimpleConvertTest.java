package ru.job4j.assertj;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {

    SimpleConvert simpleConvert;
    @BeforeEach
    public void setUp() {
        simpleConvert = new SimpleConvert();
    }


    @Test
    void toArray() {
        String[] result = simpleConvert.toArray("e1", "e2");
        assertThat(result).hasSize(2)
                .contains("e1", "e2")
                .startsWith("e1");

    }

    @Test
    void toList() {
        List<String> list = simpleConvert.toList("e1", "e2", "e3");
        assertThat(list).hasSize(3).containsOnly("e1", "e2", "e3");
    }

    @Test
    void toSet() {
        Set<String> set = simpleConvert.toSet("e1", "e2", "e3");
        assertThat(set).hasSize(3)
                .containsAnyOf("e1", "e2")
                .first().isEqualTo("e1");
    }

    @Test
    void toMap() {
        Map<String, Integer> map = simpleConvert.toMap("e0", "e1", "e2");
        assertThat(map).hasSize(3)
                .containsKeys("e0", "e1", "e2")
                .containsValues(0, 1, 2)
                .doesNotContainKey("zzz")
                .doesNotContainValue(11)
                .containsEntry("e0", 0);
    }
}