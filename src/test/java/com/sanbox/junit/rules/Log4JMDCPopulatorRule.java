package com.sanbox.junit.rules;

import org.apache.logging.log4j.ThreadContext;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

/**
 * This is Junit rule for populating Log4J MDC
 * Context so that when logging happens for tests
 * we know to which class logs belong to.
 *
 * @author Ravi
 * @see org.junit.rules.TestRule
 * @see org.junit.rules.TestWatcher
 *
 */
public class Log4jMdcPopulatorRule extends TestWatcher {
  @Override
  protected void starting(Description description) {
    super.starting(description);
    String displayName = description.getDisplayName();
    ThreadContext.put("displayName", displayName);
  }

  @Override
  protected void finished(Description description) {
    super.finished(description);
    ThreadContext.clearAll();
  }
}
