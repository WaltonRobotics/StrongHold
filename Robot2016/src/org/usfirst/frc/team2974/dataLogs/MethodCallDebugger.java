package org.usfirst.frc.team2974.dataLogs;

public class MethodCallDebugger
{
    public static void record() {
        String[] s = ClassChecker.getJavaClasses();

        for (;;) {
            for (StackTraceElement ste : Thread.currentThread().getStackTrace())
                for (String str : s)
                    if (ste.toString().contains(str) && !str.contains(MethodCallDebugger.class.getName()) && !str.contains("addMethodCall"))
                        Message.addMethodCall(ste.toString());
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
