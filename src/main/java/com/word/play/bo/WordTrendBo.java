package com.word.play.bo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WordTrendBo {

  private static final Logger logger =
      LogManager.getLogger("com.word.play.bo.WordTrendBo");

  private static Map<String, Integer> wordTrendMap = new ConcurrentHashMap<String, Integer>();
  private static final Integer ONE = 1;

  /**
   * Gets the number of times word was searched for.
   * Use of ConcurrentHashMap makes it thread safe.
   *
   * @param key word to search for
   * @return number of times word was searched for
   */
  public Integer getWordTrendingNumber(String key) {
    logger.entry(key);
    Integer answer = ONE;

    synchronized (wordTrendMap) {
      if (wordTrendMap.putIfAbsent(key, ONE) != null) {
        answer = (wordTrendMap.put(key, (wordTrendMap.get(key) + 1))) + 1;
      }
    }

    logger.exit(answer);
    return answer;
  }
}
