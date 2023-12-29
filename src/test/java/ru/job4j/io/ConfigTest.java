package ru.job4j.io;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenPairCommentEptyLine() {
        String path = "./data/pair_comment_empty.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenPairNoKey() {
        String path = "./data/pair_no_key.properties";
        Config config = new Config(path);
        assertThatThrownBy(() -> config.load())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: %s does not contain a key".formatted("=Petr Arsentev"));
    }



}