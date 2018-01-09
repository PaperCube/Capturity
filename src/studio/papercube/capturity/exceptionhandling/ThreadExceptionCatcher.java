package studio.papercube.capturity.exceptionhandling;

import studio.papercube.capturity.ui.swing.ErrorNotifier;

public class ThreadExceptionCatcher implements Thread.UncaughtExceptionHandler {
	private static ThreadExceptionCatcher ins = new ThreadExceptionCatcher();
	private ThreadExceptionCatcher() {
	}
	
	public static void register(Thread t){
		t.setUncaughtExceptionHandler(ins);
	}
	@Override
	public void uncaughtException(Thread t, Throwable e) {
		new ErrorNotifier(e.toString(),ErrorNotifier.toStackInfo(e));
	}
	
}
