package org.usfirst.frc.team2974.dataLogs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class utility class allows users to manipulate File objects
 */
public class FileHelper {

	/**
	 * This method uses the file path and creates the non-existent paths to
	 * create the end file
	 *
	 * @param path
	 *            Path to be created must have a file to be created Ex:
	 *            C:/Users/New Folder/Hello.txt
	 * @return Returns a the created file from the given path if the path was
	 *         not created then null is returned
	 */
	public static File create(String path) {
		File temp = null;

		if (path.contains(".")) {
			temp = new File(path.substring(0, path.lastIndexOf("\\")));

			if (temp.mkdirs())
				System.out.println("Managed to create directory ".concat(temp.getAbsolutePath()));
			else
				System.out.println("Did not managed to create directory ".concat(temp.getAbsolutePath()));

			temp = new File(path);

			try {
				if (temp.createNewFile())
					System.out.println("Managed to create ".concat(temp.getName()));
			} catch (IOException e) {
				System.out.println("Could not manage to create " + temp.getName());
			}
		}

		return temp;
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
	public static void writeToFile(String message, File file) {
		if (file != null && file.exists() && !message.trim().isEmpty())
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
				writer.append(message);
				writer.newLine();
			} catch (IOException e) {
				System.out.println("Could not write to file");
			}
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
		if (!File.separator.equals("\\"))
			while (path.contains("\\"))
				path = path.replace("\\", File.separator);

		return path;
	}

	/**
	 * This method checks if the specific file contains the wanted text return
	 * false if the file is null or the message is empty
	 *
	 * @param message
	 *            the String message that will be search fro in the given file
	 *            to see if it is present in the file
	 * @param file
	 *            the file from where the method will search for the message
	 * @return boolean returns true if the file contains the message or else the
	 *         method return false
	 */
	public static boolean contains(String message, File file) {
		if (file != null && !message.trim().isEmpty()) {
			List<String> list = readFromFile(file);

			if (list != null)
				for (String lines : list)
					if (lines.contains(message))
						return true;
		}
		return false;
	}

	/**
	 * Return in the form a List<String> all the lines of the given not null
	 * file if the file is null the method will return null as well
	 *
	 * @param file
	 *            the file file from where the method reads from
	 * @return List<String> returns a list of all the lines in the file
	 */
	private static List<String> readFromFile(File file) {
		if (file != null) {
			List<String> textLines = new ArrayList<>();

			try (Scanner scanner = new Scanner(file)) {
				while (scanner.hasNext())
					textLines.add(scanner.nextLine());
			} catch (Exception e) {
				System.out.println("could not read from file ".concat(file.getName()));
			}

			return textLines;
		}

		System.out.println("There was no file imputed into the readFromFile method inside the FileHelper class");
		return null;
	}
}