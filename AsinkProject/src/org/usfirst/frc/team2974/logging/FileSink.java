package org.usfirst.frc.team2974.logging;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.usfirst.frc.team2974.logging.filters.LogFilter;
import org.usfirst.frc.team2974.logging.messages.LogMessage;

public class FileSink extends LogFilter {
	String path;
	File writeFile;
	FileWriter writer;

	@Override
	public boolean LogCall(LogMessage message) {
		if (writeFile.exists()) {
			try {
				writer = new FileWriter(writeFile.getAbsolutePath(),true);
				writer.write("@" + message.getTime() + " seconds:" + message.getCommand() + ": " + message.getMessage() + "on line " + message.getLine() + "\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		}else{
			try {
				writeFile.renameTo(new File(path));
				writeFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
		}
	}

	public void setPath(String path) {
		this.path = path;
		writeFile = new File(path);
	}

	public String getPath() {
		return path;
	}
}