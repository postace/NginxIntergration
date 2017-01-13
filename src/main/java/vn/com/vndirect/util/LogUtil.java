package vn.com.vndirect.util;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Util class to write log
 * Default level of log is info
 */
public class LogUtil {

    public static void log(Level level, Object o, String message, Object... args) {
        Logger logger = Logger.getLogger(o.getClass().getSimpleName());
        logger.setLevel(level);
        String formattedMessage = String.format(message, args);
        writeLogByLevel(logger, level, formattedMessage);
    }

    private static void writeLogByLevel(Logger logger, Level level, String message) {
        if (level == Level.DEBUG) {
            logger.debug(message);
        } else if (level == Level.INFO) {
            logger.info(message);
        } else if (level == Level.WARN) {
            logger.warn(message);
        } else if (level == Level.ERROR) {
            logger.error(message);
        } else if (level == Level.FATAL) {
            logger.fatal(message);
        }
    }

}
