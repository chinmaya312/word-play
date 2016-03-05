package com.word.play.init;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class DictionaryInitializer {
  private static final Logger logger =
      LogManager.getLogger("com.word.play.init.DictionaryInitializer");

  /**
   * This method does all the grunt work of reading
   * each file in the directory path that is passed to this method.
   * It then tokenizes the lines with space and removing all non-alphabetic
   * characters.
   * Finally, it returns the Map containing the word and its frequency.
   *
   * @param folder String representing path to a folder in classpath
   * @return Map containing word frequencies
   * @throws IllegalArgumentException when the path to the file passed
   *         as param doesn't exist or is not a directory
   */
  public static Map<String, Integer> execute(String folder) {

    logger.info("Initializing Data Dictionary with files located in '{}' folder", folder);

    Map<String, Integer> wordMap = new HashMap<String, Integer>();

    URL url = DictionaryInitializer.class.getResource(folder);
    if (url == null) {
      logger.error("Fatal: '{}' folder not found in classpath", folder);
      throw new IllegalArgumentException("Resources with folder '" + folder + "' not found");
    }

    File dir;
    try {
      dir = Paths.get(url.toURI()).toFile();
    } catch (URISyntaxException e) {
      logger.error("Fatal: '{}' folder not found in classpath", folder);
      throw new IllegalArgumentException("Resources with folder '" + folder + "' not found");
    }

    if (!dir.isDirectory()) {
      logger.error("Fatal: '{}' is not a directory", folder);
      throw new IllegalArgumentException("'" + folder + "' is not a directory");
    }

    File[] files = dir.listFiles();
    for (File file: files) {
      try {
        FileInputStream fis = new FileInputStream(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));

        String line = null;
        while ((line = br.readLine()) != null) {
          line = line.replaceAll("[^A-z]", " ");
          String[] tokens = line.split(" ");
          for (String token: tokens) {
            token = token.trim().toLowerCase();
            if (token.length() != 0) {
              if (wordMap.containsKey(token)) {
                int numOfOccurences = wordMap.get(token);
                wordMap.put(token, numOfOccurences + 1);
              } else {
                wordMap.put(token, 1);
              }
            }
          }
        }

        br.close();

      } catch (FileNotFoundException fns) {
        // usually we will not hit this
        throw new IllegalArgumentException("File not Found - " + file.getName());
      } catch (IOException ioe) {
        // this might happen when files are locked from reading or insufficient permissions
        logger.error("IO Exception when reading file - " + file.getName(), ioe);
        throw new IllegalArgumentException(ioe.getMessage(), ioe);
      }
    }

    logger.info("Initializing of Data Dictionary completed");
    return wordMap;
  }
}
