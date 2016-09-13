package org.usfirst.frc.team2974.dataLogs;

/**
 * This class is used to test the WarningMessages to test that it works
 * correctly
 */
class WarningMessagesTest {

	public static void main(String[] args) {

		WarningMessages.initiateLoggerFile();

		WarningMessages.addError("Hello");
		WarningMessages.addWarning("ERROR");

		WarningMessages.printWarningsFromToday();
	}
}
