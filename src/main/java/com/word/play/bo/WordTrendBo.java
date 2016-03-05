package com.word.play.bo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WordTrendBo {

  private static Map<String, Integer> wordTrendMap = new ConcurrentHashMap<String, Integer>();
  private static final Integer ONE = 1;

  public Integer getWordTrendingNumber(String key) {
    Integer answer = ONE;
    if(wordTrendMap.containsKey(key)) {
      answer = (wordTrendMap.put(key, (wordTrendMap.get(key) + 1)))+1;
    } else {
      wordTrendMap.put(key, answer);
    }

    return answer;
  }
}
