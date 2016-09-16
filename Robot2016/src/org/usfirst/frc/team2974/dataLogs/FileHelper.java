package org.usfirst.frc.team2974.dataLogs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileHelper {
    public static File create(String path) {
        final File toReturn = new File(path);

        if (path.contains(".")) {
            for (int i = path.indexOf("\\"); i >= 0 && i < path.length(); i = path.indexOf("\\", i + 1)) {
                final File f = new File(path.substring(0, i));

                if (!f.exists())
                    if (f.mkdir())
                        System.out.println("Managed to create directory " + f.getName());
            }
        }

        if (!toReturn.exists())
            try {
                if (toReturn.createNewFile())
                    System.out.println("Managed to create file " + toReturn.getName());
            } catch (IOException e) {
                System.out.println("Did not manage to create file " + toReturn.getName());
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

        if (!File.separator.equals("\\"))
            while (path.contains("\\"))
                path = path.replace("\\", File.separator);

        return path;
    }
}