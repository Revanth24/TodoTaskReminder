package todo_code_apps.com.todotaskreminder.presentation.presenters.impl;



import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.concurrent.Future;
import java.util.regex.Pattern;

import todo_code_apps.com.todotaskreminder.domain.executor.base.Executor;
import todo_code_apps.com.todotaskreminder.domain.interactors.AddUserInteractor;
import todo_code_apps.com.todotaskreminder.domain.interactors.impl.AddUserInteractorImpl;
import todo_code_apps.com.todotaskreminder.presentation.presenters.ValidateLogin;
import todo_code_apps.com.todotaskreminder.presentation.presenters.base.AbstractPresenter;
import todo_code_apps.com.todotaskreminder.threading.MainThread;

public class ValidateLoginImpl extends AbstractPresenter implements
        AddUserInteractor.AddTaskCallback, ValidateLogin {

    private ValidateLogin.View mView;
    private GoogleApiClient mGoogleApiClient;

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    Future mFuture;

    AddUserInteractor mAddUserInteractor;
    public ValidateLoginImpl(Executor executor, MainThread mainThread,ValidateLogin.View view) {
        super(executor, mainThread);
        mView = view;
    }

    @Override
    public void onUserAdded() {
      //  mView.hideProgress();
        mView.LoginDone();
    }

    @Override
    public void onUserNotAdded() {
      //  mView.hideProgress();
        mView.LoginError();
    }

    @Override
    public void GoogleLogin(GoogleSignInResult result) {
        mAddUserInteractor = new AddUserInteractorImpl(mExecutor, mMainThread, this,null,null,false,result);

        mFuture = mAddUserInteractor.execute();
    }



    @Override
    public void LoginVerify(String userName, String Password,boolean login) {
        if(!validateUserName(userName))
        {
            mView.UsernameError();
        }
        else if(!validatePassword(Password))
        {
            mView.PasswordError();
        }
        else
        {
            mAddUserInteractor = new AddUserInteractorImpl(mExecutor, mMainThread, this,userName,Password,login,null);

            mFuture = mAddUserInteractor.execute();

           // mView.showProgress();
        }

    }

    private boolean validateUserName(String userName) {
        if (!userName.isEmpty() &&
                VALID_EMAIL_ADDRESS_REGEX.matcher(userName).find()) {
                return true;
        }
        else{
            return false;
        }
    }

    private boolean validatePassword(String password) {
        if (!password.isEmpty() && password.length() > 6 ) {
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {
        mView.hideProgress();
    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onError(String message) {
        mView.showError(message);
    }



    @Override
    public void detachView() {
        mView = null;
    }


}
