package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.function.Predicate;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3, 4, 5));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(5).containsSequence(1, 2, 3, 4, 5);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 33, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(5).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddAfterWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addAfter(input, 55, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenRemoveIf() {
        Predicate<Integer> predicate = integer -> integer != 1;
        ListUtils.removeIf(input, predicate);
        assertThat(input).hasSize(1).doesNotContainSequence(3, 4, 5);
    }

    @Test
    void whenReplaceIf() {
        Predicate<Integer> predicate = integer -> integer == 1;
        ListUtils.replaceIf(input, predicate, 666);
        assertThat(input).hasSize(4).contains(666);
    }
    @Test
    void whenRemoveAll() {
        List<Integer> elements = new ArrayList<>(List.of(1));
        ListUtils.removeAll(input, elements);
        assertThat(input).hasSize(3).doesNotContain(1);
    }


}