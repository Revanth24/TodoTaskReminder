package todo_code_apps.com.todotaskreminder.domain.interactors.base;

import java.util.concurrent.Future;

/**
 * Created by Revanth K on 18/10/18.
 */
public interface Interactor {

    /**
     * This is the main method that starts the interactor in the background thread
     */
    Future execute();

    void cancel(Future future);
}
