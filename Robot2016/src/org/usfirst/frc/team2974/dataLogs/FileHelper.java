package org.usfirst.frc.team2974.dataLogs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * This class utility class allows users to manipulate File objects
 */
public final class FileHelper {

    private FileHelper() {
    }

    /**
     * This method checks if the specific file contains the wanted text return
     * false if the file is null or the message is empty
     *
     * @param message
     *            the String message that will be search for in the given file
     *            to see if it is present in the file
     * @param file
     *            the file from where the method will search for the message
     * @return boolean returns true if the file contains the message or else the
     *         method return false
     */
    public static boolean contains(final String message, final File file) {
        if ((null != file) && !message.trim().isEmpty()) {
            final List<String> list = readFromFile(file);

            if (null != list) {
                for (String lines : list) {
                    if (lines.contains(message)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * This method uses the file path and creates the non-existent paths to
     * create the end file
     *
     * @param path
     *            Path to be created must have a file to be created Ex:
     *            C:/Users/New Folder/Hello.txt
     * @return temp Return a now created file from the path
     */
    public static File create(final String path) {
        File temp = null;

        if (path.contains(".")) {
            temp = new File(path.substring(0, path.lastIndexOf('\\')));

            if (temp.mkdirs()) {
                System.out.println("Managed to create directory " + temp.getAbsolutePath());
            } else {
                System.out.println("Did not managed to create directory " + temp.getAbsolutePath());
            }

            temp = new File(path);

            try {
                if (temp.createNewFile()) {
                    System.out.println("Managed to create " + temp.getName());
                }
            } catch (final IOException e) {
                System.out.println("Could not manage to create " + temp.getName());
            }
        }

        return temp;
    }

    /**
     * formats a path to be able to be used for the operators operating system
     * system
     *
     * @param path
     *            the path that will be formatted
     * @return path returns a path whose separators Ex: \ have been replaced to
     *         fit the operators system file separators
     */
    public static String formatFilePath(String path) {
        if (!"\\".equals(File.separator)) {
            while (path.contains("\\")) {
                path = Pattern.compile("\\\\").matcher(path).replaceAll(File.separator);
            }
        }

        return path;
    }

    /**
     * Return in the form a List<String> all the lines of the given not null
     * file if the file is null the method will return null as well
     *
     * @param file
     *            the file file from where the method reads from
     * @return List<String> returns a list of all the lines in the file
     */
    private static List<String> readFromFile(final File file) {
        if (null != file) {
            final List<String> textLines = new ArrayList<>();

            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNext()) {
                    textLines.add(scanner.nextLine());
                }
            } catch (final Exception e) {
                System.out.println("could not read from file " + file.getName());
            }

            return textLines;
        }

        System.out.println("There was no file imputed into the readFromFile method inside the FileHelper class");
        return null;
    }

    /**
     * This method allow (given a non empty string, a non null file and that the
     * file exists) it will write the given text to the specific file a new file
     * is added after every message
     *
     * @param message
     *            The String message that will be appended to the specific file
     * @param file
     *            The file where the message will be written to
     */
    public static void writeToFile(final String message, final File file) {
        if ((null != file) && file.exists() && !message.trim().isEmpty()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                writer.append(message);
                writer.newLine();
            } catch (IOException e) {
                System.out.println("Could not write to file");
            }
        }
    }
}