package com.word.play.init

import java.io.*;
// this would only be called
// from a singleton class
// never call this directly
class WordReader {
    public static Map<String, Integer> execute(String folder) {
        Map<String, Integer> wordMap = new HashMap<String, Integer>()

        File dir = new File(folder)
        dir.eachFile {
            it.eachLine { line ->
                line = line.replaceAll("[^A-z]", " ")
                def tokens = line.split(" ")
                tokens.each { token ->
                    token = token.trim().toLowerCase()
                    if(token.length() != 0) {
                        if(wordMap.containsKey(token)) {
                           def numOfOccurences = wordMap.get(token)
                           wordMap.put(token, numOfOccurences+1)
                        } else {
                            wordMap.put(token, 1)
                        }
                    }
                }
            }
        }

        wordMap.each { k, v ->
            println "${k}\t${v}"
        }

        return wordMap;
    }
}
