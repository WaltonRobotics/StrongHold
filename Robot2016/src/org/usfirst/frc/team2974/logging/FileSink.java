package org.usfirst.frc.team2974.logging;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

import org.usfirst.frc.team2974.logging.filters.LogFilter;
import org.usfirst.frc.team2974.logging.messages.LogMessage;

public class FileSink extends LogFilter {
	String path;
	File writeFile;
	FileWriter writer;

	@Override
	public boolean logCall(LogMessage message) {
		System.out.println("About to write");
		if (writer != null) {
			try {
				writer.write("@" + message.getTime() + " seconds:" + message.getCommand() + " says: "
						+ message.getMessage() + "on line " + message.getLine() + "\n" + "\n");
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("FileWriter isn't detecting the file.");
			}
			return true;
		} else {
			// try {
			// writeFile.renameTo(new File(path));
			// writeFile.createNewFile();
			// } catch (IOException e) {
			// e.printStackTrace();
			// }
			System.out.println("Error writing to the file");
			return false;
		}
	}

	public void setPath(String path) {
		if (new File("/media/sda1").exists()) {
			this.path = "/media/sda1/" + LocalDateTime.now().getMonth() + "day" + LocalDateTime.now().getDayOfMonth() + 
					"at" + LocalDateTime.now().getHour() + "minuete" + LocalDateTime.now().getMinute() + path;
		} else if (new File("/media/sda2").exists()) {
			this.path = "/media/sda2/" + LocalDateTime.now().getMonth() + "day" + LocalDateTime.now().getDayOfMonth() +
					"at" + LocalDateTime.now().getHour() + "minuete" + LocalDateTime.now().getMinute() + path;
		} else {
			System.out.println("sda1 and sda2 don't exist, therefore, the logging system is broken.");
		}
		writeFile = new File(this.path);
		try {
			writer = new FileWriter(writeFile.getAbsolutePath(), true);
		} catch (IOException e) {
			System.out.println("IOException occured within the FileSink.");
			e.printStackTrace();
		}
	}

	public String getPath() {
		return path;
	}
}