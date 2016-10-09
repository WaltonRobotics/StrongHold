package org.usfirst.frc.team2974.dataLogs;

public final class MethodCallDebugger implements Runnable {
    @Override
    public final void run() {
        final String[] s = ClassChecker.getJavaClasses();

        for (; ; ) {
            for (final StackTraceElement[] ste : Thread.getAllStackTraces().values()) {
                for (StackTraceElement ss : ste) {
                    for (String str : s) {
                        if (!ss.toString().trim().isEmpty() && ss.toString().contains(str) && !ss.toString().contains(MethodCallDebugger.class.getName()) && !ss.toString().contains("Message") && !ss.toString().contains("FileHelper")) {
                            Message.addMethodCall(ss.toString());
                        }
                    }
                }
            }
            try {
                Thread.sleep(1L);
            } catch (final InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
