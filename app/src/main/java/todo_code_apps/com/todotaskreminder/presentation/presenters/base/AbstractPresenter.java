package todo_code_apps.com.todotaskreminder.presentation.presenters.base;

import todo_code_apps.com.todotaskreminder.domain.executor.base.Executor;
import todo_code_apps.com.todotaskreminder.threading.MainThread;


/**
 * This is an abstract class which provides a template for the Presenter. This class should be
 * extended by the Presenters.
 * Created by Revanth K on 19/10/18.
 */
public abstract class AbstractPresenter {

    protected Executor mExecutor;
    protected MainThread mMainThread;

    public AbstractPresenter(Executor executor, MainThread mainThread)
    {
        mExecutor = executor;
        mMainThread = mainThread;
    }
}
