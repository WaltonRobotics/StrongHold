package org.usfirst.frc.team2974.dataLogs;

/**
 * Created by Marius Juston on 10/12/2016.
 */
final class Logger {
	private static final java.util.logging.Logger logger = java.util.logging.Logger
			.getLogger(Logger.class.getPackage().getName());

	public static java.util.logging.Logger getLogger() {
		return logger;
	}

	private Logger() {
	}
}
