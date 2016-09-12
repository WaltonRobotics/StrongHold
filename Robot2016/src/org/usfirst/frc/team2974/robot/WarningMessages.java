package org.usfirst.frc.team2974.robot;

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

/**
 * Created by Marius Juston on 8/28/2016.
 */

public class WarningMessages {
    private static File loginFile;
    //private FileWriter writer;

    public static File getLoginFile() {
        return loginFile;
    }

    public static void initiateLoggerFile() {
        loginFile = new File("Logger.txt");
    }

    public static void addWarning(String warning) {
        final Path write = Paths.get("./".concat(getLoginFile().getName()));

        warning = String.format("%-11s%19s | %s\n", "[WARNING]", getSystemTime(), warning);

        try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(write, CREATE, APPEND))) {
            out.write(warning.getBytes(), 0, warning.getBytes().length);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addError(String warning) {
        final Path write = Paths.get("./".concat(getLoginFile().getName()));

        warning = String.format("%-11s%19s | %s\n", "[ERROR]", getSystemTime(), warning);

        try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(write, CREATE, APPEND))) {
            out.write(warning.getBytes(), 0, warning.getBytes().length);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getSystemTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
    }
}