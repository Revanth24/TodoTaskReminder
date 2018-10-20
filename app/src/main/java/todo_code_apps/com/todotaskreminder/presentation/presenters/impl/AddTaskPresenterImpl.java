package todo_code_apps.com.todotaskreminder.presentation.presenters.impl;


import java.util.concurrent.Future;

import todo_code_apps.com.todotaskreminder.domain.executor.base.Executor;
import todo_code_apps.com.todotaskreminder.domain.interactors.AddTaskInteractor;
import todo_code_apps.com.todotaskreminder.domain.interactors.impl.AddTaskInteractorImpl;
import todo_code_apps.com.todotaskreminder.presentation.presenters.AddTaskPresenter;
import todo_code_apps.com.todotaskreminder.presentation.presenters.base.AbstractPresenter;
import todo_code_apps.com.todotaskreminder.threading.MainThread;

/**
 * Created by Revanth K on 19/10/18.
 */
public class AddTaskPresenterImpl<V extends AddTaskPresenter.View> extends AbstractPresenter implements
        AddTaskPresenter<V>, AddTaskInteractor.AddTaskCallback {

    private V mView;

    Future mFuture;
    AddTaskInteractor mAddTaskInteractor;

    public AddTaskPresenterImpl(Executor executor, MainThread mainThread) {
        super(executor, mainThread);
    }

    @Override
    public void onTaskAdded() {
        mView.hideProgress();
        mView.onTaskAdded();
    }

    @Override
    public void addNewTask() {

        mAddTaskInteractor = new AddTaskInteractorImpl(mExecutor, mMainThread, this);

        mFuture = mAddTaskInteractor.execute();

        mView.showProgress();
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {
        mAddTaskInteractor.cancel(mFuture);
        mFuture = null;
        mView.hideProgress();
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
    public void attachView(V view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }
}
