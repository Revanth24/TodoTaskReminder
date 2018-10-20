package todo_code_apps.com.todotaskreminder.threading.impl;

import android.os.Handler;
import android.os.Looper;

import todo_code_apps.com.todotaskreminder.threading.MainThread;

/**
 * This class will make sure that the runnable provided will be run on the Main UI thread
 * Created by Revanth K on 18/10/18.
 */
public class MainThreadImpl implements MainThread {

    private static MainThread sMainThread;
    private Handler mHandler;

    /**
     * Constructor for this class which will instantiate the android Handler
     */
    private MainThreadImpl() {
        mHandler = new Handler(Looper.getMainLooper());
    }

    /**
     * Run the runnable passed in the main UI thread
     * @param runnable The Runnable to run in the main thread
     */
    @Override
    public void post(Runnable runnable) {
        mHandler.post(runnable);
    }

    /**
     * Returns a singleton instance of this class. If the instance is not initialized, then it
     * creates and returns the instance
     * @return Instance of MainThread
     */
    public static MainThread getInstance() {
        if(sMainThread == null){
            sMainThread = new MainThreadImpl();
        }

        return sMainThread;
    }
}
