package todo_code_apps.com.todotaskreminder.domain.interactors.impl;

import java.util.Date;

import todo_code_apps.com.todotaskreminder.domain.executor.base.Executor;
import todo_code_apps.com.todotaskreminder.domain.interactors.EditTaskInteractor;
import todo_code_apps.com.todotaskreminder.domain.interactors.base.AbstractInteractor;
import todo_code_apps.com.todotaskreminder.threading.MainThread;

/**
 * Created by Revanth K on 21/10/18.
 */
public class EditTaskInteractorImpl extends AbstractInteractor implements EditTaskInteractor {

    private EditTaskInteractor.EditTaskCallback mEditTaskCallback;

    /**
     * Constructor of this class which will instantiate the mMainThread and mThreadExecutor variables
     *
     * @param threadExecutor instance of thread executor
     * @param mainThread     instance of main thread
     */
    public EditTaskInteractorImpl(Executor threadExecutor, MainThread mainThread, EditTaskCallback editTaskCallback,
                                  String title,
                                  String description,
                                  Date reminderDate,
                                  Date creationDate) {
        super(threadExecutor, mainThread);
        mEditTaskCallback = editTaskCallback;
    }

    private void notifyError(final String errMsg) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mEditTaskCallback.onTaskUpdateFailed(errMsg);
            }
        });
    }

    @Override
    public void run() {

        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mEditTaskCallback.onTaskUpdated();
            }
        });


    }
}
