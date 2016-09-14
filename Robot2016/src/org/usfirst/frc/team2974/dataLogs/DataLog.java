package org.usfirst.frc.team2974.dataLogs;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/*
 * program meant to be able to reach out to every class in every package get the getter methods and 
 * retrieve the data putting them separately in files
 * 
 * might not be useful 
 * might not be possible
 * if somebody has the knowledge of doing so please try and do it i believe that you have to
 * get the information for the current thread and read all the data passing throughout 
 * problem is when the data returned is a class and you want to record the data from that class
 */
public class DataLog {
	public static void getJavaClasses() {
		String path = new File(DataLog.class.getName()).getAbsolutePath();
		path = path.substring(0, path.lastIndexOf(DataLog.class.getPackage().getName()));

		File f = new File(path);

		ArrayList<File[]> directories = new ArrayList<>();
		directories.add(f.listFiles());
		ArrayList<File> javaFiles = new ArrayList<>();

		for (int i = 0; i < directories.size(); i++)
			for (File file : directories.get(i)) {
				File[] toAdd = file.listFiles();

				if (toAdd != null && toAdd.length != 0) {
					directories.add(toAdd);
				}
				if (file.getName().contains(".java"))
					javaFiles.add(file);
			}

		System.out.println(Arrays.toString(javaFiles.toArray()));
	}

	/*
	 * used for testing purposes public static void main(String args[]) {
	 * getJavaClasses(); }
	 */
}