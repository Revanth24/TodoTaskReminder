package todo_code_apps.com.todotaskreminder.presentation.presenters;


import todo_code_apps.com.todotaskreminder.presentation.presenters.base.BasePresenter;
import todo_code_apps.com.todotaskreminder.presentation.ui.BaseView;

/**
 * Created by Revanth K on 19/10/18.
 */
public interface AddTaskPresenter<V extends AddTaskPresenter.View> extends BasePresenter<V> {

    interface View extends BaseView {
        void onTaskAdded();
    }

    void addNewTask();

}