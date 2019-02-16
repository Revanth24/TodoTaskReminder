package todo_code_apps.com.todotaskreminder.domain.interactors;

import todo_code_apps.com.todotaskreminder.domain.interactors.base.Interactor;

public interface AddUserInteractor extends Interactor {
    interface AddUserCallback {
        void onUserAdded();
        void onUserNotAdded();
    }
}
