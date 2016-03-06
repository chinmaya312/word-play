package com.word.play.bo;

import com.word.play.sandbox.Log4jMdcPopulator;
import net.jodah.concurrentunit.Waiter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeoutException;

import static org.junit.Assert.assertTrue;

public class WordTrendBoTest {

  private static final int NUMBER_OF_THREADS = 100;
  private static final Logger logger =
      LogManager.getLogger("com.word.play.bo.WordTrendBoTest");

  @Rule
  public Log4jMdcPopulator log4JMdcPopulator = new Log4jMdcPopulator();

  private final Waiter waiter = new Waiter();
  private WordTrendBo wordTrendBo;

  @Before
  public void init() {
    wordTrendBo = new WordTrendBo();
  }

  @Test
  public void test1TimeWord() {
    logger.entry();
    Integer number = wordTrendBo.getWordTrendingNumber("one");
    assertTrue("First time call should return 1", number == 1);
    logger.exit();
  }

  @Test
  public void test2TimeWord() {
    logger.entry();
    wordTrendBo.getWordTrendingNumber("two");
    Integer number = wordTrendBo.getWordTrendingNumber("two");
    assertTrue("Second time call should return 2", number == 2);
    logger.exit();
  }

  @Test
  public void test3MT1TimeWord() throws TimeoutException {

    logger.entry();
    final List<Integer> collector = new ArrayList<Integer>(NUMBER_OF_THREADS);

    for (int i = 0; i < NUMBER_OF_THREADS; i++) {
      new Thread(new Runnable() {
        @Override
        public void run() {
          Integer trend = wordTrendBo.getWordTrendingNumber("multi-thread");
          collector.add(trend);
          waiter.resume();
        }
      }).start();
    }

    waiter.await(NUMBER_OF_THREADS * 1000, NUMBER_OF_THREADS);
    assertTrue("List size should be equal to NUMBER_OF_THREADS",
        collector.size() == NUMBER_OF_THREADS);

    Set<Integer> set = new HashSet<Integer>(collector);
    assertTrue("Should not have any duplicates, found "
        + (NUMBER_OF_THREADS - set.size()) + " duplicate(s)!!",
        set.size() == NUMBER_OF_THREADS);

    logger.exit();
  }
}
