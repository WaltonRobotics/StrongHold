package org.usfirst.frc.team2974.dataLogs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHelper {
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

	public static void writeToFile(String message, File file) {
		if (file != null && !message.trim().isEmpty())
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
				writer.append(message);
				writer.newLine();
			} catch (IOException e) {
				System.out.println("Could not write to file");
			}
	}

	public static String formatFilePath(String path) {
		if (!File.separator.equals("\\"))
			while (path.contains("\\"))
				path = path.replace("\\", File.separator);

		return path;
	}

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

	public static List<String> readFromFile(File file) {
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

		System.out.println("There was no file inputed into the readFromFile methjod inside the FileHelper class");
		return null;
	}
}