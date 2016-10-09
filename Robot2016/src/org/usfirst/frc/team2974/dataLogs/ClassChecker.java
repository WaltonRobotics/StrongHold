package org.usfirst.frc.team2974.dataLogs;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;

public final class ClassChecker
{
    public static String[] getJavaClasses()
    {
        final ArrayList<String> p = new ArrayList<>();

        getFiles(p, Paths.get("").toAbsolutePath().toString(), ".java", 0);

        return p.toArray(new String[p.size()]);
    }

    public static void getFiles(final ArrayList<String> toLoad, final String path, final String extension, final int i)
    {
        final File f = new File(path);

        if (f.exists())
        {
            final File[] files = f.listFiles();

            if((null != files) && (0 < files.length))
            {
                for(final File file: files)
                {
                    if(file.getName().contains(extension)) {
                        toLoad.add(file.getName().substring(0, file.getName().indexOf(extension)));
                    } else {
                        getFiles(toLoad, file.getAbsolutePath(), extension, i);
                    }
                }
            }
        }
    }
}
