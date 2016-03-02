package com.word.play.init

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

import java.io.*
import java.nio.file.Paths;
// this would only be called
// from a singleton class
// never call this directly
class WordReader {

    private static final Logger logger = LogManager.getLogger("com.word.play.init.WordReader")

    public static Map<String, Integer> execute(String folder) {

        Map<String, Integer> wordMap = new HashMap<String, Integer>()

        URL url = ClassLoader.getSystemResource(folder);
        if(url == null) {
            Throwable throwable = new IllegalArgumentException("Resources with folder '${folder}' not found")
            logger.throwing(throwable)
            throw throwable
        }
        File dir = Paths.get(url.toURI()).toFile();

        if(!dir.isDirectory()) {
            Throwable throwable = new IllegalArgumentException("'${folder}' is not a directory")
            logger.throwing(throwable)
            throw throwable
        }

        //File dir = new File(folder)
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

        return wordMap;
    }
}
