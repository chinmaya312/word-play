package com.word.play.app;


import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class WordPlayApplication extends Application {
  public Set<Class<?>> getClasses() {
    Set<Class<?>> classes = new HashSet<Class<?>>();
    classes.add( com.word.play.server.WordService.class );
    return classes;
  }
}
