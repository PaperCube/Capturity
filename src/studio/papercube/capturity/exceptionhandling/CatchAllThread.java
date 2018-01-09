package studio.papercube.capturity.exceptionhandling;

public class CatchAllThread extends Thread {
	public CatchAllThread() {
		super();
		ThreadExceptionCatcher.register(this);
	}

	public CatchAllThread(Runnable target, String name) {
		super(target, name);
		ThreadExceptionCatcher.register(this);
	}

	public CatchAllThread(Runnable target) {
		super(target);
		ThreadExceptionCatcher.register(this);
	}

	public CatchAllThread(String name) {
		super(name);
		ThreadExceptionCatcher.register(this);
	}

	public CatchAllThread(ThreadGroup group, Runnable target, String name, long stackSize) {
		super(group, target, name, stackSize);
		ThreadExceptionCatcher.register(this);
	}

	public CatchAllThread(ThreadGroup group, Runnable target, String name) {
		super(group, target, name);
		ThreadExceptionCatcher.register(this);
	}

	public CatchAllThread(ThreadGroup group, Runnable target) {
		super(group, target);
		ThreadExceptionCatcher.register(this);
	}

	public CatchAllThread(ThreadGroup group, String name) {
		super(group, name);
		ThreadExceptionCatcher.register(this);
	}
}
