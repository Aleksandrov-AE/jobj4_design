package ru.job4j.kiss.fool;


import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class FoolPrivateTest {

    @Test
    void getStartAtcorrectAndWrong() throws Exception {
        Method m = Fool.class.getDeclaredMethod("getStartAt", int.class, String.class);
        m.setAccessible(true);
        int next = (int) m.invoke(null, 1, "2");
        assertEquals(2, next);
        int reset = (int) m.invoke(null, 2, "2");
        assertEquals(0, reset);
    }

    @Test
    void fizzBuzzAtCorrect() throws Exception {
        Method m = Fool.class.getDeclaredMethod("fizzBuzz", int.class);
        m.setAccessible(true);
        String number = (String) m.invoke(null, 1);
        assertEquals("1", number);
        String fizzBuzz = (String) m.invoke(null, 15);
        assertEquals("FizzBuzz", fizzBuzz);
    }
}
