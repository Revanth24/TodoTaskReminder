package todo_code_apps.com.todotaskreminder.presentation.presenters;

import java.util.Date;

import todo_code_apps.com.todotaskreminder.presentation.presenters.base.BasePresenter;
import todo_code_apps.com.todotaskreminder.presentation.ui.BaseView;

/**
 * Created by Revanth K on 21/10/18.
 */
public interface EditTaskPresenter extends BasePresenter{

    interface View extends BaseView {
        void onTaskUpdated();
    }

    void editTask(String title, String description, Date reminderDate, Date creationDate);
}
