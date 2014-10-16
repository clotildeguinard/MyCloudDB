package test;

import tools.Loggable;

import org.apache.log4j.Level;

public class TestLogging extends Loggable {

	/**
	 * @return Shows how the logger works;
	 */
	private void testLogger() {

		logger.removeAppender("file");
		logger.warn("This is warn");
		logger.error("This is error");
		logger.fatal("This is fatal");

	}

	public static void main(String[] args) {
		TestLogging obj = new TestLogging();
		obj.testLogger();
	}
}
