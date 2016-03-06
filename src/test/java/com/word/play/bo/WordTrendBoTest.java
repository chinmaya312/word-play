package com.word.play.bo;

import net.jodah.concurrentunit.Waiter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeoutException;

import static org.junit.Assert.assertTrue;

public class WordTrendBoTest {

  private static final Logger logger =
      LogManager.getLogger("com.word.play.bo.WordTrendBoTest");


  private final int NUM_OF_THREADS = 100;
  private final Waiter waiter = new Waiter();
  private WordTrendBo wordTrendBo;

  @Before
  public void init() {
    wordTrendBo = new WordTrendBo();
  }

  @Test
  public void test1TimeWord() {
    Integer number = wordTrendBo.getWordTrendingNumber("search");
    assertTrue("First time call should return 1", number == 1);
  }

  @Test
  public void test2TimeWord() {
    wordTrendBo.getWordTrendingNumber("with");
    Integer number = wordTrendBo.getWordTrendingNumber("with");
    assertTrue("First time call should return 1", number == 2);
  }

  @Test
  public void test3MT1TimeWord() throws TimeoutException {

    final List<Integer> collector = new ArrayList<Integer>(NUM_OF_THREADS);

    for(int i=0; i<NUM_OF_THREADS; i++) {
      new Thread(new Runnable() {
        @Override
        public void run() {
          Integer trend = wordTrendBo.getWordTrendingNumber("thread");
          collector.add(trend);
          //logger.info("{}, {}", prTrend, trend);
          //waiter.assertTrue(trend>prTrend);
          //prTrend = trend;
          waiter.resume();
        }
      }).start();
    }

    waiter.await(NUM_OF_THREADS*1000, NUM_OF_THREADS);
    assertTrue("List size should be equal to NUM_OF_THREADS", collector.size() == NUM_OF_THREADS);

    Set<Integer> set = new HashSet<Integer>(collector);
    assertTrue("Should not have any duplicates, found " + (NUM_OF_THREADS - set.size()) + " duplicate(s)!!", set.size() == NUM_OF_THREADS);
  }
}
