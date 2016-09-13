package org.usfirst.frc.team2974.dataLogs;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class WarningMessages {
	private static File loginFile;
	private static Path write;
	private static File dir;

	public static void addError(String warning) {
		message(true, warning);
	}

	public static void addWarning(String warning) {
		message(false, warning);
	}

	private static void checker() {
		if (getDir() == null)
			initiateLoggerFile();

		else if (getLoginFile() == null)
			initiateLoggerFile();
	}

	public static void deleteAll() {
		if (getDir() != null)
			deleteDir(getDir());

		else
			System.out.println(
					"Directory has not been initiated yet please insert the WarningMessages.initiateLoggerFile() method before this one");
	}

	private static void deleteDir(File file) {
		File[] contents = file.listFiles();
		if (contents != null)
			for (File f : contents) {
				deleteDir(f);

				if (f.exists())
					System.out.println(f.getName() + " at " + f.getAbsolutePath() + " has not been deleted");

				else
					System.out.println(f.getName() + " at " + f.getAbsolutePath() + " has been deleted");
			}

		file.delete();

		if (file.exists())
			System.out.println(file.getName() + " at " + file.getAbsolutePath() + " has not been deleted");

		else
			System.out.println(file.getName() + " at " + file.getAbsolutePath() + " has been deleted");
	}

	private static String getDate() {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyy_MM_dd"));
	}

	private static String getDateMessageStyle() {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyy/MM/dd"));
	}

	private static File getDir() {
		return dir;
	}

	private static File getLoginFile() {
		return loginFile;
	}

	private static Path getPath() {
		return write;
	}

	private static String getSystemTime() {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
	}

	public static void initiateLoggerFile() {
		dir = new File("Logs");

		if (!dir.exists())
			if (dir.mkdir())
				System.out.println("Manage to add folder " + dir.getName() + " to " + dir.getAbsolutePath());
			else
				System.out.println("Did not manage to add directory.");
		else {
			System.out.println("Directory " + dir.getName() + " already exists");

			long dirSize = getSize(0);

			System.out.println("Directory size is currently " + dirSize + " bytes == " + dirSize / 1000 + " kilobytes");

			if (dir.length() / 100 >= 10) {
				System.out.println(
						"Director is " + dirSize / 100 + ". The folder is too large, the folder will be purged.");
				deleteAll();
			}
		}

		loginFile = new File("Logs\\Logger".concat(getDate()).concat(".txt"));

		if (!loginFile.exists())
			try {
				if (loginFile.createNewFile())
					System.out.println("Managed to create " + loginFile.getName());
			} catch (IOException e) {
				System.out.println("Did not manage to create to create " + loginFile.getName());
			}
		else
			System.out.println(loginFile.getName() + " already exists");

		write = Paths.get("./Logs/".concat(getLoginFile().getName()));
	}

	private static int getSize(long s) {
		int size = 0;

		if (getDir() != null) {
			File[] contents = getDir().listFiles();

			if (contents != null)
				for (File f : contents)
					size += f.length();
		}

		return size;
	}

	private static void message(boolean error, String warning) {
		warning = String.format("%-11s%19s | %s\n", error ? "[ERROR]" : "[WARNING]", getSystemTime(), warning);

		if (getLoginFile() == null)
			reinitializeSequence(error, warning);

		try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(getPath(), CREATE, APPEND))) {
			out.write(warning.getBytes(), 0, warning.getBytes().length);
		} catch (IOException e) {
			System.out.println("Did not manage to write to " + getLoginFile().getName()
					+ "reinitializing will delete current file and create a new one, all current logs will be deleted.");
			reinitializeSequence(error, warning);
		}
	}

	public static void printWarningsFromToday() {
		// ITable table = new ITable();
		File dir = new File("Logs");

		if (dir.exists()) {
			File file = new File(dir.getName().concat("\\Logger".concat(getDate()).concat(".txt")));

			if (file.exists()) {
				try (Scanner scanner = new Scanner(file)) {
					if (file.exists())
						while (scanner.hasNext()) {
							String message = scanner.nextLine();
							if (message.contains(getDateMessageStyle()))
								System.out.println(message);
						}
				} catch (FileNotFoundException e) {
					System.out.println("Cannot read from file");
				}
			}
		}
	}

	private static void reinitializeSequence(boolean error, String message) {
		checker();
		deleteDir(getLoginFile());
		initiateLoggerFile();
		message(error, message);
	}
}