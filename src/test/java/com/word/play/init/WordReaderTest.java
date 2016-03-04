package com.word.play.init;

import com.sanbox.junit.rules.Log4jMdcPopulatorRule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * This is Test class for WorReader
 *
 * @author Ravi
 * @version 1.0
 * @see WordReader
 */
public class WordReaderTest {

  @Rule
  public Log4jMdcPopulatorRule log4JMdcPopulatorRule = new Log4jMdcPopulatorRule();

  @Rule
  public ExpectedException thrown= ExpectedException.none();

  private static final Logger logger = LogManager.getLogger();

  /**
   * TC01 - Test the loading of resources.
   */
  @Test
  public void testLoadFiles() {
    logger.entry();
    Map<String, Integer> map = WordReader.execute("testdata/wordreader/tc01");
    assertTrue("Map size should be greater than zero", map.size() > 0);
    logger.exit();
  }

   /**
   * TC02 - Predetermined text as below.
   * Brown fox jumped on green Green 'gREEn' Fox
   *          Greener than "GreEn"
   * brown occurs 1 time
   * fox occurs 2 times
   * jumped occurs 1 time
   * on occurs 1 time
   * green occurs 4 times
   * greener occurs 1 time
   * than occurs 1 time
   */
  @Test
  public void testFrequencies() {
    logger.entry();
    Map<String, Integer> map = WordReader.execute("testdata/wordreader/tc02");
    assertTrue("brown should occur once", map.get("brown") == 1);
    assertTrue("fox should occur 2 times", map.get("fox") == 2);
    assertTrue("jumped should occur once", map.get("jumped") == 1);
    assertTrue("on should occur once", map.get("on") == 1);
    assertTrue("green should occur 4 times", map.get("green") == 4);
    assertTrue("greener should occur once", map.get("greener") == 1);
    assertTrue("than should occur once", map.get("than") == 1);
    logger.exit();
  }

  /**
   * TC03 - Invoke WordReader with folder name
   * that is not found in classpath.
   */
  @Test
  public void testDirNotInClassPath() {
    logger.entry();
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("Resources with folder 'no-folder' not found");
    WordReader.execute("no-folder");
    logger.exit();
  }

  /**
   * TC03 - Invoke WordReader with folder name
   * that is not found in classpath.
   */
  @Test
  public void testNonDirInClassPath() {
    logger.entry();
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("'testdata/wordreader/tc02/tc02.txt' is not a directory");
    WordReader.execute("testdata/wordreader/tc02/tc02.txt");
    logger.exit();
  }
}
