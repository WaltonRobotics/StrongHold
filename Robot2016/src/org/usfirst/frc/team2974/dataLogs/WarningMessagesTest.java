package org.usfirst.frc.team2974.dataLogs;

/**
 * This class is used to test the WarningMessages to test that it works
 * correctly
 */
class WarningMessagesTest {

	public static void main(String[] args) {

		WarningMessages.initiateLoggerFile();

		// for (int i = 0; i < 100; i++) {
		// WarningMessages.addError("Hello wowo you have ice long hair how do
		// you do it?");
		// WarningMessages.addWarning("ERROR your name bob not joeye and my name
		// is marius!!!");
		// }
		
		for(int i =0; i < 10000; i++)
		WarningMessages.addError("Hello wowo you have ice long hair how do");

		WarningMessages.printWarningsFromToday();
	}
}
