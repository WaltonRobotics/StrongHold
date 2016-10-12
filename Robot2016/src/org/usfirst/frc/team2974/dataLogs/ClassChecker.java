package org.usfirst.frc.team2974.dataLogs;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;

final class ClassChecker {

	private static final String JAVA_FILE_EXTENSION_ID = ".java";

	private static void getFiles(final ArrayList<String> toLoad, final String path, final String extension) {
		final File f = new File(path);

		if (f.exists()) {
			final File[] files = f.listFiles();

			if (files != null && files.length > 0)
				for (final File file : files)
					if (file.getName().contains(extension))
						toLoad.add(file.getName().substring(0, file.getName().indexOf(extension)));
					else
						getFiles(toLoad, file.getAbsolutePath(), extension);
		}
	}

	public static String[] getJavaClasses() {
		final ArrayList<String> p = new ArrayList<>();

		getFiles(p, Paths.get("").toAbsolutePath().toString(), JAVA_FILE_EXTENSION_ID);

		return p.toArray(new String[p.size()]);
	}
}
