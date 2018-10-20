package todo_code_apps.com.todotaskreminder.presentation.presenters.base;

import todo_code_apps.com.todotaskreminder.presentation.ui.BaseView;

/**
 * Created by Revanth K on 19/10/18.
 */
public interface BasePresenter<V extends BaseView> {
    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onResume() method.
     */
    void resume();

    /**
     * Method that controls the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onPause() method.
     */
    void pause();

    /**
     * Method that controls the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onStop() method.
     */
    void stop();

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onDestroy() method.
     */
    void destroy();


    /**
     * Method that should signal the appropriate view to show the appropriate error with the provided message.
     */
    void onError(String message);

    /**
     * Method which attaches the view to the presenter
     * @param view The view that needs to attached to the presenter
     */
    void attachView(V view);

    /**
     * Method which detaches the view from the presenter
     */
    void detachView();
}
