package org.usfirst.frc.team2974.dataLogs;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * This class used is a utility class that allows you to log information such
 * as: errors, warnings and actions the code will give
 */
public final class Message {

    private static final String WARNING_LOGS = "Warning Logs";
    private static final String WARNING = "[WARNING]";
    private static final String YYYY_MM_DD_HH_MM_MM = "yyyy/MM/dd HH:mm:mm";
    private static final String S_19S_S_S = "%-9s %19s %s | %s\n";
    private static final String YYYY_MM_DD = "yyyy_MM_dd";
    private static final String DESKTOP_LOGS_LOG = "\\Desktop\\Logs\\Log";
    private static final String ERROR_LOGS = "Error Logs";
    private static final String ERROR = "[ERROR]";
    private static final String ACTION_LOGS = "Action Logs";
    private static final String ACTION = "[ACTION]";
    private static final String METHOD_CALL_LOGS = "Method call logs";
    private static final String METHOD_CALL = "[Method Call]";

    /**
     * this method allows you to log a specific action to the Action Logs.txt
     * file in the Logs directory on the desktop
     *
     * @param message that string message that will be recorded in a given file
     */
    public static void addAction(final String message) {
        Message.addAction(message, null);
    }

    /**
     * this method allows you to log a specific action to the Action Logs.txt
     * file and from which class the method was called (this information is
     * usually entered using "this" as the parameter for the object variable) to
     * the Logs directory on the desktop
     *
     * @param message that string message that will be recorded in a given file
     * @param object  gives more information to the user by telling him/her where
     *                the method was executed
     */
    public static void addAction(final String message, final Object object) {
        Message.addMessage(Message.ACTION, message, object, Message.ACTION_LOGS);
    }

    /**
     * this method allows you to log a specific error to the Error Logs.txt file
     * in the Logs directory on the desktop
     *
     * @param message that string message that will be recorded in a given file
     */
    public static void addError(final String message) {
        Message.addError(message, null);
    }

    /**
     * this method allows you to log a specific error to the Error Logs.txt file
     * and from which class the method was called (this information is usually
     * entered using "this" as the parameter for the object variable) to the
     * Logs directory on the desktop
     *
     * @param message that string message that will be recorded in a given file
     * @param object  gives more information to the user by telling him/her where
     *                the method was executed
     */
    public static void addError(final String message, final Object object) {
        Message.addMessage(Message.ERROR, message, object, Message.ERROR_LOGS);
    }

    /**
     * this method allows you to log a specific error to the Method call logs.txt file
     * and from which class the method was called (this information is usually
     * entered using "this" as the parameter for the object variable) to the
     * Logs directory on the desktop
     *
     * @param message that string message that will be recorded in a given file
     */
    public static void addMethodCall(final String message) {
        Message.addMethodCall(message, null);
    }

    /**
     * this method allows you to log a specific error to the Method call logs.txt file
     * and from which class the method was called (this information is usually
     * entered using "this" as the parameter for the object variable) to the
     * Logs directory on the desktop
     *
     * @param message that string message that will be recorded in a given file
     * @param object  gives more information to the user by telling him/her where
     *                the method was executed
     */
    public static void addMethodCall(final String message, final Object object) {
        Message.addMessage(Message.METHOD_CALL, message, object, Message.METHOD_CALL_LOGS);
    }

    /**
     * This method creates file with the given file name and writes the
     * Information in a specific format with the time and the class information
     * if given.
     *
     * @param what     tells the user what kind of log the message is Ex: [ERROR],
     *                 [WARNING] or [ACTION]
     * @param message  that string message that will be recorded in a given file
     * @param object   gives more information to the user by telling him/her where
     *                 the method was executed
     * @param fileName decides what the file that will be created will be named
     */
    private static void addMessage(final String what, String message, final Object object, final String fileName) {
        final File file = FileHelper
                .create(FileHelper.formatFilePath(System.getProperty("user.home") + Message.DESKTOP_LOGS_LOG + Message.getSystemTime(Message.YYYY_MM_DD) + '\\' + fileName + ".txt"));

        if (/*
             * is removed for now but if helpful uncomment this piece
			 * !FileHelper.contains(message, file) &&
			 */!message.trim().isEmpty()) {
            message = String.format(Message.S_19S_S_S, what, Message.getSystemTime(Message.YYYY_MM_DD_HH_MM_MM),
                    (object == null) ? "" : object.getClass().getName(), message);

            FileHelper.writeToFile(message, file);

            System.out.println(Pattern.compile("\n").matcher(message).replaceAll(""));
        }
    }

    /**
     * this method allows you to log a specific warning to the Warning Logs.txt
     * file in the Logs directory on the desktop
     *
     * @param message that string message that will be recorded in a given file
     */
    public static void addWarning(final String message) {
        Message.addWarning(message, null);
    }

    /**
     * this method allows you to log a specific warning to the Warning Logs.txt
     * file and from which class the method was called (this information is
     * usually entered using "this" as the parameter for the object variable) to
     * the Logs directory on the desktop
     *
     * @param message that string message that will be recorded in a given file
     * @param object  gives more information to the user by telling him/her where
     *                the method was executed
     */
    public static void addWarning(final String message, final Object object) {
        Message.addMessage(Message.WARNING, message, object, Message.WARNING_LOGS);
    }

	/*
     * yyyy/MM/dd HH:mm:ss yyyy_MM_dd
	 */

    /**
     * This method return the system time in a specific format such as:
     * yyyy/MM/dd HH:mm:ss or yyyy_MM_dd. y is the year, M is the month, d is
     * the day, H is the hour, m is the minutes, and s are the seconds.
     *
     * @param pattern is the parameter that will format the time to be shown a
     *                specific way
     * @return the time given in a certain format
     */
    private static String getSystemTime(final String pattern) {
        return new SimpleDateFormat(pattern).format(new Date(System.currentTimeMillis()));
    }
}