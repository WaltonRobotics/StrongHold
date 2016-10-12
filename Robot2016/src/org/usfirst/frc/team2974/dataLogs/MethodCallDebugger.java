package org.usfirst.frc.team2974.dataLogs;//package org.usfirst.frc.team2974.dataLogs;

final class MethodCallDebugger implements Runnable {
	private final String[] availableJavaClasses;

	public MethodCallDebugger() {
		availableJavaClasses = ClassChecker.getJavaClasses();
	}

	@Override
	public final void run() {

		// StackTraceElement prev = null;
		// Thread prev = null;

		// while (true) {
		// for (final StackTraceElement[] ste :
		// Thread.getAllStackTraces().values()) {
		// if(ste.length > 0 ) {
		// StackTraceElement ss = ste[0];
		//
		// if (prev != null && !ss.equals(prev))
		// for (String str : s) {
		// if (!ss.toString().trim().isEmpty() &&
		// !ss.toString().contains(MethodCallDebugger.class.getPackage().getName()))
		// {
		// dataLogs.Message.addMethodCall(ss.toString());
		// }
		// }
		//
		// prev = ss;
		// }

		// for (final Thread ste : Thread.getAllStackTraces().keySet()) {
		// if(ste.isAlive()) {
		// if (prev != null && !ste.equals(prev))
		// for (String str : s) {
		// if (!ss.toString().trim().isEmpty()) {
		// dataLogs.Message.addMethodCall(ss.toString());
		// }
		// }
		//
		// prev = ste;
		// }
		// }
		// try {
		// Thread.sleep(0L);
		// } catch (final InterruptedException e) {
		// e.printStackTrace();
		// }
		// }
		// final String[] s = ClassChecker.getJavaClasses();

		for (;;) {
			for (final StackTraceElement[] ste : Thread.getAllStackTraces().values())
				for (final StackTraceElement ss : ste)
					if (!ss.toString().trim().isEmpty()
							&& !ss.toString().contains(MethodCallDebugger.class.getPackage().getName()))
						Message.addMethodCall(ss.toString());

			try {
				Thread.sleep(1L);
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
