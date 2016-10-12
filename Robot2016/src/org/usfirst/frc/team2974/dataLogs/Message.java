package org.usfirst.frc.team2974.dataLogs;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * This class used is a utility class that allows you to log information such
 * as: errors, warnings and actions the code will give
 */
public enum Message {
	WARNING(Constants.WARNING_ID, FileHelper
			.create(FileHelper.formatFilePath(Constants.DESKTOP_LOG_FOLDER_PATH + " Log\\Warning Logs.txt"))), ERROR(
					Constants.ERROR_ID,
					FileHelper.create(FileHelper
							.formatFilePath(Constants.DESKTOP_LOG_FOLDER_PATH + " Log\\Error Logs.txt"))), ACTION(
									Constants.ACTION_ID,
									FileHelper.create(FileHelper.formatFilePath(
											Constants.DESKTOP_LOG_FOLDER_PATH + " Log\\Action Logs.txt"))), METHOD_CALL(
													Constants.METHOD_CALL_ID,
													FileHelper.create(
															FileHelper.formatFilePath(Constants.DESKTOP_LOG_FOLDER_PATH
																	+ " Log\\Method Logs.txt")));

	private static final class Constants {

		private static final String DESKTOP_LOG_FOLDER_PATH = System.getProperty("user.home") + "\\Desktop\\Logs\\"
				+ getSystemTime(YYYY_MM_DD);
		private static final String WARNING_ID = "[WARNING]";
		private static final String METHOD_CALL_ID = "[Method Call]";
		private static final String ACTION_ID = "[ACTION]";
		private static final String ERROR_ID = "[ERROR]";
	}

	private static final Logger LOGGER = Logger.getLogger(Message.class.getPackage().getName());

	private static final String YYYY_MM_DD_HH_MM_MM = "yyyy/MM/dd HH:mm:mm";

	private static final String S_19S_S_S = "%-9s %19s %s | %s\n";

	private static final String YYYY_MM_DD = "yyyy_MM_dd";

	/**
	 * this method allows you to log a specific action to the Action Logs.txt
	 * file in the Logs directory on the desktop
	 *
	 * @param message
	 *            that string message that will be recorded in a given file
	 */
	public static void addAction(final String message) {
		addAction(message, null);
	}

	/**
	 * this method allows you to log a specific action to the Action Logs.txt
	 * file and from which class the method was called (this information is
	 * usually entered using "this" as the parameter for the object variable) to
	 * the Logs directory on the desktop
	 *
	 * @param message
	 *            that string message that will be recorded in a given file
	 * @param object
	 *            gives more information to the user by telling him/her where
	 *            the method was executed
	 */
	private static void addAction(final String message, final Object object) {
		addMessage(ACTION, message, object);
	}

	/**
	 * this method allows you to log a specific error to the Error Logs.txt file
	 * in the Logs directory on the desktop
	 *
	 * @param message
	 *            that string message that will be recorded in a given file
	 */
	public static void addError(final String message) {
		addError(message, null);
	}

	/**
	 * this method allows you to log a specific error to the Error Logs.txt file
	 * and from which class the method was called (this information is usually
	 * entered using "this" as the parameter for the object variable) to the
	 * Logs directory on the desktop
	 *
	 * @param message
	 *            that string message that will be recorded in a given file
	 * @param object
	 *            gives more information to the user by telling him/her where
	 *            the method was executed
	 */
	private static void addError(final String message, final Object object) {
		addMessage(ERROR, message, object);
	}

	/**
	 * This method creates file with the given file name and writes the
	 * Information in a specific format with the time and the class information
	 * if given.
	 *
	 * @param what
	 *            tells the user what kind of log the message is Ex: [ERROR],
	 *            [WARNING] or [ACTION]
	 * @param message
	 *            that string message that will be recorded in a given file
	 * @param object
	 *            gives more information to the user by telling him/her where
	 *            the method was executed
	 */
	private static void addMessage(final Message what, String message, final Object object) {
		if (/*
			 * is removed for now but if helpful uncomment this piece
			 * !dataLogs.FileHelper.contains(message, file) &&
			 */!message.trim().isEmpty()) {
			message = String.format(S_19S_S_S, what.getID(), getSystemTime(YYYY_MM_DD_HH_MM_MM),
					object == null ? "" : object.getClass().getName(), message);

			FileHelper.writeToFile(message, what.getMyFile());

			LOGGER.info(Pattern.compile("\n").matcher(message).replaceAll(""));
		}
	}

	/**
	 * this method allows you to log a specific error to the Method call
	 * logs.txt file and from which class the method was called (this
	 * information is usually entered using "this" as the parameter for the
	 * object variable) to the Logs directory on the desktop
	 *
	 * @param message
	 *            that string message that will be recorded in a given file
	 */
	public static void addMethodCall(final String message) {
		addMethodCall(message, null);
	}

	/**
	 * this method allows you to log a specific error to the Method call
	 * logs.txt file and from which class the method was called (this
	 * information is usually entered using "this" as the parameter for the
	 * object variable) to the Logs directory on the desktop
	 *
	 * @param message
	 *            that string message that will be recorded in a given file
	 * @param object
	 *            gives more information to the user by telling him/her where
	 *            the method was executed
	 */
	private static void addMethodCall(final String message, final Object object) {
		addMessage(METHOD_CALL, message, object);
	}

	/**
	 * this method allows you to log a specific warning to the Warning Logs.txt
	 * file in the Logs directory on the desktop
	 *
	 * @param message
	 *            that string message that will be recorded in a given file
	 */
	public static void addWarning(final String message) {
		addWarning(message, null);
	}

	/**
	 * this method allows you to log a specific warning to the Warning Logs.txt
	 * file and from which class the method was called (this information is
	 * usually entered using "this" as the parameter for the object variable) to
	 * the Logs directory on the desktop
	 *
	 * @param message
	 *            that string message that will be recorded in a given file
	 * @param object
	 *            gives more information to the user by telling him/her where
	 *            the method was executed
	 */
	private static void addWarning(final String message, final Object object) {
		addMessage(WARNING, message, object);
	}

	/**
	 * This method return the system time in a specific format such as:
	 * yyyy/MM/dd HH:mm:ss or yyyy_MM_dd. y is the year, M is the month, d is
	 * the day, H is the hour, m is the minutes, and s are the seconds.
	 *
	 * @param pattern
	 *            is the parameter that will format the time to be shown a
	 *            specific way
	 * @return the time given in a certain format
	 */
	static String getSystemTime(final String pattern) {
		return new SimpleDateFormat(pattern).format(new Date(System.currentTimeMillis()));
	}

	private final String ID;

	private final File myFile;

	Message(final String id, final File file) {
		ID = id;
		myFile = file;
	}

	private String getID() {
		return ID;
	}

	private File getMyFile() {
		return myFile;
	}

	@Override
	public String toString() {
		return "Message{" + "ID='" + getID() + '\'' + ", myFile=" + getMyFile() + '}';
	}
}