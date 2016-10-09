package org.usfirst.frc.team2974.dataLogs;

public class MethodCallDebugger extends Thread
{
    public void run() {
        String[] s = ClassChecker.getJavaClasses();

        for (;;) {
            for (StackTraceElement[] ste : Thread.getAllStackTraces().values())
            	for(StackTraceElement ss: ste)
                for (String str : s)
                    if (!ss.toString().trim().isEmpty() && ss.toString().contains(str) && !ss.toString().contains(MethodCallDebugger.class.getName()) && !ss.toString().contains("addMethodCall"))
                        Message.addMethodCall(ss.toString());
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
