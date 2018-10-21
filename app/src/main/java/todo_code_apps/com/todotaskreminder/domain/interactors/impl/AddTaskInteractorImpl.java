package todo_code_apps.com.todotaskreminder.domain.interactors.impl;

import java.util.Date;

import todo_code_apps.com.todotaskreminder.domain.executor.base.Executor;
import todo_code_apps.com.todotaskreminder.domain.interactors.AddTaskInteractor;
import todo_code_apps.com.todotaskreminder.domain.interactors.base.AbstractInteractor;
import todo_code_apps.com.todotaskreminder.domain.model.Task;
import todo_code_apps.com.todotaskreminder.threading.MainThread;

/**
 * Created by Revanth K on 18/10/18.
 */
public class AddTaskInteractorImpl extends AbstractInteractor implements AddTaskInteractor{

    private AddTaskCallback mAddTaskCallback;
    private String mTitle;
    private String mDescription;
    private Date mReminderDate;
    private Date mCreationDate;

    /**
     * Constructor of this class which will instantiate the main thread and thread executor variables
     *
     * @param threadExecutor
     * @param mainThread
     */
    public AddTaskInteractorImpl(Executor threadExecutor, MainThread mainThread,
                                 AddTaskCallback addTaskCallback,
                                 String title,
                                 String description,
                                 Date reminderDate,
                                 Date creationDate) {
        super(threadExecutor, mainThread);
        mAddTaskCallback = addTaskCallback;
        mTitle = title;
        mDescription = description;
        mReminderDate = reminderDate;
        mCreationDate = creationDate;
    }

    private void notifyError(final String errMsg) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mAddTaskCallback.onTaskAddFailed(errMsg);
            }
        });
    }

    @Override
    public void run() {

        Task task = new Task(mTitle, mDescription, mReminderDate, mCreationDate);

        // Insert the task into the storage using the repository

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            notifyError("Thread Exception");
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
