package org.usfirst.frc.team2974.dataLogs;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class used is a utility class that allows you to log information such
 * as: errors, warnings and actions the code will give
 * 
 */
public class Message {

	/**
	 * this method allows you to log a specific action to the Action Logs.txt
	 * file in the Logs directory on the desktop
	 * 
	 * @param message
	 */
	public static void addAction(String message) {
		addAction(message, null);
	}

	/**
	 * this method allows you to log a specific action to the Action Logs.txt
	 * file and from which class the method was called (this information is
	 * usually entered using "this" as the parameter for the object variable) to
	 * the Logs directory on the desktop
	 * 
	 * @param message
	 * @param object
	 */
	public static void addAction(String message, Object object) {
		addMessage("[ACTION]", message, object, "Action Logs");
	}

	/**
	 * this method allows you to log a specific error to the Error Logs.txt file
	 * in the Logs directory on the desktop
	 * 
	 * @param message
	 */
	public static void addError(String message) {
		addError(message, null);
	}

	/**
	 * this method allows you to log a specific error to the Error Logs.txt file
	 * and from which class the method was called (this information is usually
	 * entered using "this" as the parameter for the object variable) to the
	 * Logs directory on the desktop
	 * 
	 * @param message
	 * @param object
	 */
	public static void addError(String message, Object object) {
		addMessage("[ERROR]", message, object, "Error Logs");
	}

	/**
	 * This method creates file with the given file name and writes the
	 * Information in a specific format with the time and the class information
	 * if given.
	 * 
	 * @param what
	 * @param message
	 * @param object
	 * @param fileName
	 */
	private static void addMessage(String what, String message, Object object, String fileName) {
		File file = FileHelper
				.create(FileHelper.formatFilePath(System.getProperty("user.home").concat("\\Desktop\\Logs\\Log"
						.concat(getSystemTime("yyyy_MM_dd").concat("\\".concat(fileName).concat(".txt"))))));

		if (!FileHelper.contains(message, file) && !message.trim().isEmpty()) {
			message = String.format("%-9s %19s %s | %s\n", what, getSystemTime("yyyy/MM/dd HH:mm:mm"),
					object == null ? "" : object.getClass().getName(), message);

			FileHelper.writeToFile(message, file);

			System.out.println(message.replaceAll("\n", ""));
		}
	}

	/**
	 * this method allows you to log a specific warning to the Warning Logs.txt
	 * file in the Logs directory on the desktop
	 * 
	 * @param message
	 */
	public static void addWarning(String message) {
		addWarning(message, null);
	}

	/**
	 * this method allows you to log a specific warning to the Warning Logs.txt
	 * file and from which class the method was called (this information is
	 * usually entered using "this" as the parameter for the object variable) to
	 * the Logs directory on the desktop
	 * 
	 * @param message
	 * @param object
	 */
	public static void addWarning(String message, Object object) {
		addMessage("[WARNING]", message, object, "Warning Logs");
	}

	/*
	 * yyyy/MM/dd HH:mm:ss yyyy_MM_dd
	 */

	/**
	 * This method return the system time in a specific format such as:
	 * yyyy/MM/dd HH:mm:ss or yyyy_MM_dd. y is the year, M is the month, d is
	 * the day, H is the hour, m is the minutes, and s are the seconds.
	 * 
	 * @param pattern
	 * @return
	 */
	private static String getSystemTime(String pattern) {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
	}
}