package org.usfirst.frc.team2974.dataLogs;

public class MethodCallDebugger
{
    public static void record() {
        String[] s = ClassChecker.getJavaClasses();

        for (;;) {
            for (StackTraceElement[] ste : Thread.getAllStackTraces().values())
            	for(StackTraceElement ss: ste)
                for (String str : s)
                    if (ss.toString().contains(str) && !ss.toString().contains(MethodCallDebugger.class.getName()) && !ss.toString().contains("addMethodCall"))
                        Message.addMethodCall(ste.toString());
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
