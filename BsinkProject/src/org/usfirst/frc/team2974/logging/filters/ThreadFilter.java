package org.usfirst.frc.team2974.logging.filters;

import java.util.Stack;
import java.util.concurrent.Semaphore;

import org.usfirst.frc.team2974.logging.messages.LogMessage;

public class ThreadFilter extends LogFilter implements Runnable {
	Stack<LogMessage> stackLogMessages;
	Semaphore stackSemaphore = new Semaphore(0);
	Thread thread = new Thread(this);
	boolean isRunning = true;
	
	public ThreadFilter()
	{
		thread.run();
	}
	public void run() {
		// acquire the semaphore
		// Pop the logmessage from the stack
		// call LogPostFilter with message
		while( isRunning) 
		{
			try {
				stackSemaphore.acquire();
				LogMessage poppedmsg = stackLogMessages.pop();
				LogPostFilter(poppedmsg);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean LogCall(LogMessage message) {
		// Push logMessage to the queue
		stackLogMessages.push(message);
		// Release Semaphore resource
		stackSemaphore.release();
		return true;
	}

	/**
	*
	*
	*
	*/
}
