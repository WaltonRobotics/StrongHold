package org.usfirst.frc.team2974.dataLogs;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
	public static void addAction(String message) {
		addAction(message, null);
	}

	public static void addAction(String message, Object object) {
		addMessage("[ACTION]", message, object, "Action Logs");
	}

	public static void addError(String message) {
		addError(message, null);
	}

	public static void addError(String message, Object object) {
		addMessage("[ERROR]", message, object, "Error Logs");
	}

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

	public static void addWarning(String message) {
		addWarning(message, null);
	}

	public static void addWarning(String message, Object object) {
		addMessage("[WARNING]", message, object, "Warning Logs");
	}

	/*
	 * yyyy/MM/dd HH:mm:ss yyyy_MM_dd
	 */

	private static String getSystemTime(String pattern) {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
	}
}