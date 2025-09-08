package ru.job4j.kiss.fool;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FoolTest {

    @Test
    void whenNumberNotDivisible() {
        assertEquals("1", Fool.fizzBuzz(1));
        assertEquals("2", Fool.fizzBuzz(2));
        assertEquals("4", Fool.fizzBuzz(4));
    }

    @Test
    void whenNumberDivisibleBy3() {
        assertEquals("Fizz", Fool.fizzBuzz(3));
        assertEquals("Fizz", Fool.fizzBuzz(6));
        assertEquals("Fizz", Fool.fizzBuzz(9));
    }

    @Test
    void whenNumberDivisibleBy5() {
        assertEquals("Buzz", Fool.fizzBuzz(5));
        assertEquals("Buzz", Fool.fizzBuzz(10));
        assertEquals("Buzz", Fool.fizzBuzz(20));
    }

    @Test
    void whenNumberDivisibleBy3And5() {
        assertEquals("FizzBuzz", Fool.fizzBuzz(15));
        assertEquals("FizzBuzz", Fool.fizzBuzz(30));
        assertEquals("FizzBuzz", Fool.fizzBuzz(45));
    }

    @Test
    void whenCorrectAnswerthenIncrement() {
        int startAt = 1;
        int result = Fool.getStartAt(startAt, "2");
        assertEquals(2, result);
    }

    @Test
    void whenWrongAnswerthenResetTo0() {
        int startAt = 1;
        int result = Fool.getStartAt(startAt, "Fizz");
        assertEquals(0, result);
    }

    @Test
    void whenCorrectFizzBuzzAnswerthenIncrement() {
        int startAt = 14;
        int result = Fool.getStartAt(startAt, "FizzBuzz");
        assertEquals(15, result);
    }

    @Test
    void whenWrongBuzzAnswerthenResetTo0() {
        int startAt = 9;
        int result = Fool.getStartAt(startAt, "10");
        assertEquals(0, result);
    }
}