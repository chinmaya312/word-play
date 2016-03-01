package com.word.play.init

import java.io.*;
// this would only be called
// from a singleton class
// never call this directly
class WordReader {
    public static Map<String, Integer> execute() {
        Map<String, Integer> wordMap = new HashMap<String, Integer>()

        File dir = new File("txtfiles")
        dir.eachFile {
            println it.name
        }

        return wordMap;
    }
}
