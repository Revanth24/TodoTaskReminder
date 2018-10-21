package todo_code_apps.com.todotaskreminder.domain.interactors.base;

import java.util.concurrent.Future;

/**
 * The methods that needs to be implemented by any interactor should be added here
 * Created by Revanth K on 18/10/18.
 */
public interface Interactor {

    /**
     * This is the main method that starts the interactor in the background thread
     */
    Future execute();

    /**
     * This is used to cancel an interactor operation
     * @param future The Future object that was returned on executing interactor
     */
    void cancel(Future future);
}
