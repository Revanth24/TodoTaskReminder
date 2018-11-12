package todo_code_apps.com.todotaskreminder.domain.interactors;

import todo_code_apps.com.todotaskreminder.domain.interactors.base.Interactor;

/**
 * Created by Revanth K on 18/10/18.
 */
public interface AddTaskInteractor extends Interactor
{
    interface AddTaskCallback
    {
        void onTaskAdded();
        void onTaskAddFailed(String errMsg);
    }
}
