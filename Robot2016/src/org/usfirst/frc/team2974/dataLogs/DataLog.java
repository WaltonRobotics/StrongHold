package org.usfirst.frc.team2974.dataLogs;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;


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
    
// used for testing purposes
//    public static void main(String args[])
//    {
//        getJavaClasses();
//    }
}