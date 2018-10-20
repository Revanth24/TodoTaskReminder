package todo_code_apps.com.todotaskreminder.domain.executor.base;

import java.util.concurrent.Future;

import todo_code_apps.com.todotaskreminder.domain.interactors.base.AbstractInteractor;

/**
 * This is responsible for running the interactors in the background thread
 * Created by Revanth K on 18/10/18.
 */
public interface Executor {

    /**
     * This method should call the interactor's run method and thus start the interactor. This should be called
     * on a background thread as interactors might do lengthy operations.
     *
     * @param interactor The interactor to run.
     */
    Future execute(final AbstractInteractor interactor);

    void cancel(Future futures);
}
