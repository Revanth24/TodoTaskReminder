package todo_code_apps.com.todotaskreminder.domain.interactors;

import todo_code_apps.com.todotaskreminder.domain.interactors.base.Interactor;

/**
 * Created by Revanth K on 21/10/18.
 */
public interface EditTaskInteractor extends Interactor {

    interface EditTaskCallback {
        void onTaskUpdated();
        void onTaskUpdateFailed(String errMsg);
    }
}
