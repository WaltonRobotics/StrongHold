package org.usfirst.frc.team2974.dataLogs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileHelper {
	public static File create(String path) {
		File toReturn = new File(path);

		if (path.contains(".")) {
			for (int i = path.indexOf("\\"); i >= 0 && i < path.length(); i = path.indexOf("\\", i + 1)) {
				File f = new File(path.substring(0, i));

				if (!f.exists())
					f.mkdir();
			}
		}

		if (!toReturn.exists())
			try {
				toReturn.createNewFile();
			} catch (IOException e) {
				System.out.println("did not manage to craete file");
			}

		return toReturn;
	}

	public static void writeToFile(String message, File file) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
			writer.append(message);
			writer.newLine();
		} catch (IOException e) {
			System.out.println("Could not write to file");
		}
	}

	public static String formatFilePath(String path) {
		return System.getProperty("os.name").toLowerCase().contains("window") ? path.replaceAll("/", "\\\\")
				: path.replaceAll("\\\\", "/");
	}
}