package todo_code_apps.com.todotaskreminder.presentation.presenters.impl;

import java.util.Date;
import java.util.concurrent.Future;

import todo_code_apps.com.todotaskreminder.domain.executor.base.Executor;
import todo_code_apps.com.todotaskreminder.domain.interactors.EditTaskInteractor;
import todo_code_apps.com.todotaskreminder.domain.interactors.impl.EditTaskInteractorImpl;
import todo_code_apps.com.todotaskreminder.presentation.presenters.EditTaskPresenter;
import todo_code_apps.com.todotaskreminder.presentation.presenters.base.AbstractPresenter;
import todo_code_apps.com.todotaskreminder.threading.MainThread;

/**
 * Created by Revanth K on 21/10/18.
 */
public class EditTaskPresenterImpl extends AbstractPresenter implements EditTaskPresenter, EditTaskInteractor.EditTaskCallback{

    private View mView;
    private Future mFuture;


    public EditTaskPresenterImpl(Executor executor, MainThread mainThread, View view) {
        super(executor, mainThread);
        mView = view;
    }

    @Override
    public void editTask(String title, String description, Date reminderDate, Date creationDate) {
        EditTaskInteractor editTaskInteractor = new EditTaskInteractorImpl(mExecutor,
                mMainThread,
                this,
                title,
                description,
                reminderDate,
                creationDate);
        mFuture = editTaskInteractor.execute();

        if(mView != null) {
            mView.showProgress();
        }
    }

    @Override
    public void onTaskUpdated() {

    }

    @Override
    public void onTaskUpdateFailed(String errMsg) {

        if(mView != null) {
            mView.hideProgress();
        }
        onError(errMsg);
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void detachView() {

    }
}
