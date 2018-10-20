package todo_code_apps.com.todotaskreminder.domain.interactors.impl;

import java.util.Calendar;

import todo_code_apps.com.todotaskreminder.domain.executor.base.Executor;
import todo_code_apps.com.todotaskreminder.domain.interactors.AddTaskInteractor;
import todo_code_apps.com.todotaskreminder.domain.interactors.base.AbstractInteractor;
import todo_code_apps.com.todotaskreminder.domain.model.Task;
import todo_code_apps.com.todotaskreminder.threading.MainThread;

/**
 * Created by Revanth K on 18/10/18.
 */
public class AddTaskInteractorImpl extends AbstractInteractor implements AddTaskInteractor{

    private AddTaskInteractor.AddTaskCallback mAddTaskCallback;

    /**
     * Constructor of this class which will instantiate the main thread and thread executor variables
     *
     * @param threadExecutor
     * @param mainThread
     */
    public AddTaskInteractorImpl(Executor threadExecutor, MainThread mainThread,
                                 AddTaskCallback addTaskCallback) {
        super(threadExecutor, mainThread);
        mAddTaskCallback = addTaskCallback;
    }

    @Override
    public void run() {

        Task task = new Task("Title1", "Adding my First task", Calendar.getInstance().getTime());

        // Insert the task into the storage using the repository

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();

            return;
        }

        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mAddTaskCallback.onTaskAdded();
            }
        });
    }
}
