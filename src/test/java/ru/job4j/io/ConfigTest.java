package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "data/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect")).isEqualTo("org.hibernate.dialect.=PostgreSQLDialect");
    }

    @Test
    void whenPairWithComment() {
        String path = "data/app1.properties";
        Config config = new Config(path);
        config.load();
        assertThatThrownBy(() -> config.value("    #PostgreSQLhibernate.dialect"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Not value");
    }


    @Test
    void whenPairWithoutEqual() {
        String path = "data/app3.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Illegal prop");
    }

    @Test
    void whenPairWithEmptyKey() {
        String path = "data/app4.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Illegal prop");
    }

    @Test
    void whenPairWithEmptyValue() {
        String path = "data/app5.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Illegal prop");
    }
}