package tools;

import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
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
	protected ConsoleAppender consoleAppender;

	public Loggable() {
		logger = Logger.getLogger(this.getClass());

		createNewFileAppender("all");
		createNewFileAppender("warn");
		createNewFileAppender("fatal");
		createNewConsoleAppender("all");
	}

	/**
	 * 
	 * @param level
	 * @return True if succeeded, false if consoleAppender is null
	 */
	protected boolean setConsoleLevel(String level) {
		ConsoleAppender ca = (ConsoleAppender) logger.getAppender("MyConsoleAppender");
		if (ca != null) {
			ca.setThreshold(Level.toLevel(level));
			return true;
		} return false;
	}

	private static FileAppender createNewFileAppender(String logLevel) {
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

	private static ConsoleAppender createNewConsoleAppender(String logLevel) {
	    ConsoleAppender appender = new ConsoleAppender();
	    appender.setName("MyConsoleAppender");
	    appender.setLayout(new PatternLayout("%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n"));
	    appender.setThreshold(Level.toLevel(logLevel));
	    appender.activateOptions();

	    logger.addAppender(appender);
	    return appender;
	}
}
