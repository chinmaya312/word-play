package com.word.play.init

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

import java.nio.file.Paths

/**
 * This would only be invoked
 * by an application scoped singleton
 * object.
 *
 * Assumption is that the path of the file
 * passed would be a directory in the classpath with
 * list of files.
 *
 * @author Ravi
 * @version 1.0
 */
class WordReader {

    private static final Logger logger = LogManager.getLogger("com.word.play.init.WordReader")

    /**
     * This method does all the grunt work of reading
     * each file in the directory path that is passed to this method.
     *
     * It then tokenizes the lines with space and removing all non-alphabetic
     * characters.
     *
     * Finally, it returns the Map containing the word and its frequency.
     *
     * @param folder String representing path to a folder in classpath
     * @return Map containing word frequencies
     * @throws IllegalArgumentException when the path to the file passed as param doesn't exist or is not a directory
     */
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
