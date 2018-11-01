package todo_code_apps.com.todotaskreminder.presentation.presenters;

import todo_code_apps.com.todotaskreminder.presentation.presenters.base.BasePresenter;
import todo_code_apps.com.todotaskreminder.presentation.ui.BaseView;

public interface ValidateLogin extends BasePresenter {
    interface View extends BaseView{
        void LoginDone();
        void UsernameError();
        void PasswordError();
        void LoginError();
    }
    void LoginVerify(String userName, String Password);
}
