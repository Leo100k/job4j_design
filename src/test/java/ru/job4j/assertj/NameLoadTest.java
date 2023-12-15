package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkNotEmpty() {
        String[] names = new String[]{"key=value", "key1=value1", "key2=value2"};
        NameLoad nameLoad = new NameLoad();
        nameLoad.parse(names);
        Map<String, String> expected = new HashMap<>();
        expected.put("key", "value");
        expected.put("key1", "value1");
        expected.put("key2", "value2");
        assertThat(nameLoad.getMap()).isEqualTo(expected);

    }

    @Test
    void validateNoDelimiter() {
        String[] names = new String[]{"key=value", "key1:value1", "key2=value2"};
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: %s does not contain the symbol '='".formatted("key1:value1"));
    }

    @Test
    void validateNotContainKey() {
        String[] names = new String[]{"key=value", "=value1", "key2=value2"};
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: %s does not contain a key".formatted("=value1"));
    }

    @Test
    void validateNotContainValue() {
        String[] names = new String[]{"key=value", "key1=", "key2=value2"};
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: %s does not contain a value".formatted("key1="));
    }
}