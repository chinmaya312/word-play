package com.word.play.init;

import org.junit.Test;

import java.util.Map;

public class WordReaderTest {

    @Test
    public void testLoadFiles() {
        Map<String, Integer> map = WordReader.execute();
        System.out.println(map.size());
    }
}
