package com.sanbox.junit.rules;

import org.apache.logging.log4j.ThreadContext;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class Log4JMDCPopulatorRule extends TestWatcher {
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
