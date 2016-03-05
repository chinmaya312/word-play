package com.word.play.server;

import com.word.play.init.DictionaryInitializer;

import java.util.Map;
import javax.ejb.Singleton;
import javax.ejb.Startup;


@Singleton
@Startup
public class WordFrequencyServer {

  private final Map<String, Integer> dataDictionary = DictionaryInitializer.execute("/txtfiles");
  static final Integer ZERO = 0;

  /**
   * Get frequency of the key that was passed to the method.
   *
   * @param key - Get the frequency of the word from the
   *            dictionary map intialized during startup
   * @return frequency of the 'key'
   */
  public Integer getFrequency(final String key) {
    if (dataDictionary.containsKey(key)) {
      return dataDictionary.get(key);
    } else {
      return ZERO;
    }
  }
}
