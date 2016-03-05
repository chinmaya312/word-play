package com.word.play.app;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;

public class WordPlayApplication extends Application {
  public Set<Class<?>> getClasses() {
    Set<Class<?>> classes = new HashSet<Class<?>>();
    classes.add(com.word.play.server.WordService.class);
    return classes;
  }
}
