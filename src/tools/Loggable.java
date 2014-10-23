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
	protected static FileAppender mainAppender;

	public enum AvailableLevels {
		ALL, DEBUG, INFO, WARN, ERROR, FATAL, OFF
	}

	public Loggable() {
		logger = Logger.getLogger(this.getClass());

		createNewFileAppender("debug");
		createNewFileAppender("warn");
		createNewFileAppender("fatal");
		mainAppender = createNewFileAppender("all");
		mainAppender.setFile(logPath + "mainLog" + ".log");

		logger.debug("Successfully initiated logger");
	}

	/**
	 * 
	 * @param level
	 * @return True if succeeded, false if mainAppender is null or entered level does not exist
	 */
	public static boolean setMainAppenderLevel(String level) {
		try {
			if (mainAppender != null && AvailableLevels.valueOf(level.toUpperCase()) != null) {
				mainAppender.setThreshold(Level.toLevel(level));
				logger.info("Level of main appender set to " + level);
				return true;
			} 
		} catch (IllegalArgumentException e){
			logger.error("Tried to set log level to unexisting level : " + level);
		}
		return false;
	}

	/**
	 * 
	 * @return mainAppender level if mainAppender not null
	 */
	public static String getMainAppenderLevel() {
		if (mainAppender != null) {
			return mainAppender.getThreshold().toString();
		} return null;
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
}
