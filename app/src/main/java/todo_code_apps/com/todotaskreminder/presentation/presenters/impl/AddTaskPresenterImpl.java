package todo_code_apps.com.todotaskreminder.presentation.presenters.impl;


import java.util.Date;
import java.util.concurrent.Future;

import todo_code_apps.com.todotaskreminder.domain.executor.base.Executor;
import todo_code_apps.com.todotaskreminder.domain.interactors.AddTaskInteractor;
import todo_code_apps.com.todotaskreminder.domain.interactors.impl.AddTaskInteractorImpl;
import todo_code_apps.com.todotaskreminder.presentation.presenters.AddTaskPresenter;
import todo_code_apps.com.todotaskreminder.presentation.presenters.base.AbstractPresenter;
import todo_code_apps.com.todotaskreminder.presentation.ui.BaseView;
import todo_code_apps.com.todotaskreminder.threading.MainThread;

/**
 * Created by Revanth K on 19/10/18.
 */
public class AddTaskPresenterImpl extends AbstractPresenter implements
        AddTaskPresenter, AddTaskInteractor.AddTaskCallback {

    private AddTaskPresenter.View mView;
    private Future mFuture;
    private AddTaskInteractor mAddTaskInteractor;

    public AddTaskPresenterImpl(Executor executor, MainThread mainThread, AddTaskPresenter.View view) {
        super(executor, mainThread);
        mView = view;
    }

    @Override
    public void onTaskAdded() {
        if(mView != null) {
            mView.hideProgress();
            mView.onTaskAdded();
        }
    }

    @Override
    public void onTaskAddFailed(String errMsg) {
        if(mView != null) {
            mView.hideProgress();
        }
        onError(errMsg);
    }

    @Override
    public void addNewTask(String title, String description, Date reminderDate, Date creationDate) {

        mAddTaskInteractor = new AddTaskInteractorImpl(mExecutor,
                mMainThread,
                this,
                title,
                description,
                reminderDate,
                creationDate);

        mFuture = mAddTaskInteractor.execute();

        if(mView != null) {
            mView.showProgress();
        }
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {
        mAddTaskInteractor.cancel(mFuture);
        mFuture = null;

        if(mView != null) {
            mView.hideProgress();
        }
    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onError(String message) {
        mView.showError(message);
    }

    @Override
    public void detachView() {
        mView = null;
    }

}
