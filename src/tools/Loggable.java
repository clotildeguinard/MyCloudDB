package tools;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.Priority;
import org.apache.log4j.RollingFileAppender;

import ui.Application;

/**
 * Class whose children will have a logger
 * @author Clotilde
 *
 */
public abstract class Loggable {
	protected static Logger logger;
	protected final static String logPath = "logs/";

	public Loggable() {
		logger = Logger.getLogger(this.getClass());

		createNewAppender("all");
		createNewAppender("warn");
		createNewAppender("fatal");
	}

	private static FileAppender createNewAppender(String logLevel) {
	    RollingFileAppender appender = new RollingFileAppender();
	    appender.setName("MyFileAppender" + logLevel);
	    appender.setLayout(new PatternLayout("%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n"));
	    appender.setFile(logPath + logLevel + ".log");
	    appender.setAppend(true);
	    appender.setMaxFileSize("5MB");
	    appender.setMaxBackupIndex(10);
	    appender.setThreshold(Level.toLevel(logLevel));
	    appender.activateOptions();

	    logger.addAppender(appender);
	    return appender;
	}
}
