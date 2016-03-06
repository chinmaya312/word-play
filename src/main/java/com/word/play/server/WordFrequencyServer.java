package com.word.play.server;

import com.word.play.init.DictionaryInitializer;

import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Startup
@Singleton
public class WordFrequencyServer {

  private Map<String, Integer> dataDictionary;
  static final Integer ZERO = 0;

  @PostConstruct
  public void init() {
    dataDictionary = DictionaryInitializer.execute("/txtfiles");
  }

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
