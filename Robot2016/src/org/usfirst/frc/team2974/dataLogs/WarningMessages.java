package org.usfirst.frc.team2974.dataLogs;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class WarningMessages {
    private static File loginFile;

    public static File getLoginFile() {
        return loginFile;
    }

    public static void initiateLoggerFile() {
        File dir = new File("Logs");

        if (!dir.exists())
            if(dir.mkdir())
                System.out.println("Manage to add folder " + dir.getName() + " to " + dir.getAbsolutePath());
            else
                System.out.println("Did not manage to add directory.");


        loginFile = new File("Logger".concat(getDate()).concat(".txt"));
    }

    public static void addWarning(String warning) {
        final Path write = Paths.get("./Logs/".concat(getLoginFile().getName()));

        warning = String.format("%-11s%19s | %s\n", "[WARNING]", getSystemTime(), warning);

        try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(write, CREATE, APPEND))) {
            out.write(warning.getBytes(), 0, warning.getBytes().length);
        } catch (IOException e) {
            System.out.println("Did not manage to write to " + getLoginFile().getName());
        }
    }

    public static void addError(String warning) {
        final Path write = Paths.get("./Logs/".concat(getLoginFile().getName()));

        warning = String.format("%-11s%19s | %s\n", "[ERROR]", getSystemTime(), warning);

        try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(write, CREATE, APPEND))) {
            out.write(warning.getBytes(), 0, warning.getBytes().length);
        } catch (IOException e) {
            System.out.println("Did not manage to write to " + getLoginFile().getName());
        }
    }

    private static String getSystemTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
    }

    private static String getDate() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyy_MM_dd"));
    }
}