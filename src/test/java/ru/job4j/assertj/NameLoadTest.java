package ru.job4j.assertj;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {

    @Test
    void parseArrayEmpty() {
        String[] strings = new String[0];
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse(strings))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("empty");
    }

    @Test
    void parseNoContainSymbol() {
        String[] strings = {"key=Value", "   Key= Value", "keyValue"};
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse(strings))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("keyValue");


    }

    @Test
    void parseNoContainKey() {
        String[] strings = {"key=Value", "   key = Value", "=Value"};
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse(strings))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("=Value");
    }

    @Test
    void parseNoContainValue() {
        String[] strings = {"key="};
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse(strings))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("key=");
    }

    @Test
    void getMap() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }


}