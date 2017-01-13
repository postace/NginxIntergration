package vn.com.vndirect.util;

import org.apache.log4j.Level;
import org.junit.Test;

public class LogUtilTest {

    @Test
    public void testDebugLog() {
        LogUtil.log(Level.DEBUG, this, "My message is %s", " not found!");
    }

    @Test
    public void testInfoLog(){
        LogUtil.log(Level.INFO, this, "Much more information");
    }

    @Test
    public void testWarnLog() {
        LogUtil.log(Level.WARN, this, "This method() could behave like you couldn't imagine");
    }

    @Test
    public void testErrorLog() {
        LogUtil.log(Level.ERROR, this, "Total %s errors", "5");
    }

    @Test
    public void testFatalLog() {
        LogUtil.log(Level.FATAL, this, "You should recover your data immediately");
    }
}