package todo_code_apps.com.todotaskreminder.threading;

/**
 * This interface will define a class that will enable the interactor to run some operation in the main UI thread
 * Created by Revanth K on 18/10/18.
 */
public interface MainThread {

    /**
     * This method should contain the logic that needs to be notified to the main UI thread.
     * @param runnable The Runnable to run in the main thread
     */
    void post(final Runnable runnable);
}
