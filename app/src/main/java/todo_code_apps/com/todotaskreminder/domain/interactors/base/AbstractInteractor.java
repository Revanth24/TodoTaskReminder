package todo_code_apps.com.todotaskreminder.domain.interactors.base;

import java.util.concurrent.Future;

import todo_code_apps.com.todotaskreminder.domain.executor.base.Executor;
import todo_code_apps.com.todotaskreminder.threading.MainThread;

/**
 * This is an abstract class which provides a template for the Interactor. This class should be
 * extended by the Interactors.
 * Created by Revanth K on 18/10/18.
 */
public abstract class AbstractInteractor implements Interactor{

    protected Executor   mThreadExecutor;
    protected MainThread mMainThread;

    protected volatile boolean mIsCanceled;
    protected volatile boolean mIsRunning;

    /**
     * Constructor of this class which will instantiate the mMainThread and mThreadExecutor variables
     * @param threadExecutor instance of thread executor
     * @param mainThread instance of main thread
     */
    public AbstractInteractor(Executor threadExecutor, MainThread mainThread) {
        mThreadExecutor = threadExecutor;
        mMainThread = mainThread;
    }

    /**
     * This method logic should be written by the extending classes.
     *
     * It should contain actual business logic of interactor operation.
     * This method will be called by execute method of the ThreadExecutor which
     * in turn will be called by this class's execute method
     */
    public abstract void run();

    /**
     * Cancel the interactor operation
     */
    @Override
    public void cancel(Future future) {
        mThreadExecutor.cancel(future);
        mIsCanceled = true;
        mIsRunning = false;
    }

    /**
     * To mark the completion of the interactor operation
     */
    public void onFinished() {
        mIsRunning = false;
        mIsCanceled = false;
    }

    /**
     * To get the status of the interactor whether running or not
     * @return the running status of the interactor. true : running; false : not running
     */
    public boolean isRunning() {
        return mIsRunning;
    }

    /**
     * Starts the this interactor using the thread executor. The thread executor will in turn call
     * run method of this class
     */
    @Override
    public Future execute() {
        // mark this interactor as running
        this.mIsRunning = true;

        // start running this interactor in a background thread
        return mThreadExecutor.execute(this);
    }
}
