package com.word.play.server;

import com.word.play.init.DictionaryIntializer;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.Map;

@Singleton
@Startup
public class WordFrequencyServer {

  final private Map<String, Integer> dataDictionary = DictionaryIntializer.execute("/txtfiles");
  final static Integer ZERO = new Integer(0);

  public Integer getFrequency(final String key) {
    if(dataDictionary.containsKey(key)) return dataDictionary.get(key);
    else return ZERO;
  }
}
