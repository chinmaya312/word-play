package com.word.play.bo;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class WordTrendBoTest {

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
}
