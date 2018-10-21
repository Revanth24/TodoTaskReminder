package todo_code_apps.com.todotaskreminder.presentation.presenters;


import java.util.Date;

import todo_code_apps.com.todotaskreminder.presentation.presenters.base.BasePresenter;
import todo_code_apps.com.todotaskreminder.presentation.ui.BaseView;

/**
 * Created by Revanth K on 19/10/18.
 */
public interface AddTaskPresenter extends BasePresenter {

    /**
     * The callback methods for the views
     */
    interface View extends BaseView {
        void onTaskAdded();
    }

    /**
     * The methods that are mandatory to be implemented by this presenter should be added below
     */

    /**
     * This method should be called from the view to add a new task
     * @param title
     * @param description
     * @param reminderDate
     * @param creationDate
     */
    void addNewTask(String title, String description, Date reminderDate, Date creationDate);

}
