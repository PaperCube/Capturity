package capturity.capture;

import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Supplier;

/**
 * Created by imzhy on 2016/4/10.
 */
//FIXME
    @Deprecated
abstract class Controller extends TimerTask{
    protected TimerTask currentTask = null;
    protected Timer timer;
    protected long interval = 0;
    protected long delay = 0;
    protected Supplier<TimerTask> createTimerTask;
    protected boolean isTaskSuspended = false;

    protected void suspendTask(){
        if (currentTask != null)  currentTask.cancel();
        isTaskSuspended=true;
    }

    protected void resumeTask(){
        if(isTaskSuspended) timer.schedule(createTimerTask.get(),delay,interval);
        isTaskSuspended=false;
    }


}
