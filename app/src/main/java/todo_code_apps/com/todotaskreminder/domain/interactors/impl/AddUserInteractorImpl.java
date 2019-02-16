package todo_code_apps.com.todotaskreminder.domain.interactors.impl;



import todo_code_apps.com.todotaskreminder.domain.executor.base.Executor;
import todo_code_apps.com.todotaskreminder.domain.interactors.AddUserInteractor;
import todo_code_apps.com.todotaskreminder.domain.interactors.base.AbstractInteractor;
import todo_code_apps.com.todotaskreminder.domain.model.GoogleLogin;
import todo_code_apps.com.todotaskreminder.domain.model.Login;
import todo_code_apps.com.todotaskreminder.domain.model.LoginListener;
import todo_code_apps.com.todotaskreminder.domain.model.SignUp;
import todo_code_apps.com.todotaskreminder.threading.MainThread;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;

public class AddUserInteractorImpl extends AbstractInteractor implements AddUserInteractor, LoginListener {

    private AddUserCallback mAddUserCallback;
    private String userName;
    private String Password;
    private boolean login;
    private GoogleSignInResult result;


    public AddUserInteractorImpl(Executor threadExecutor, MainThread mainThread, AddUserCallback addUserCallback, String userName, String password, boolean login, GoogleSignInResult result) {
        super(threadExecutor, mainThread);
        this.userName = userName;
        this.Password = password;
        this.mAddUserCallback = addUserCallback;
        this.login = login;
        this.result= result;

    }

    @Override
    public void run() {
        if(result != null)
        {
            if (result.isSuccess()) {
                // successful -> authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                GoogleLogin mGoogleLogin = new GoogleLogin(this);
                mGoogleLogin.firebaseAuthWithGoogle(account);
            } else {
                // failed -> update UI

            }

        }
        else if(login) {
            Login mLogin = new Login(userName, Password, this);
            mLogin.authenticate();
        }
        else
        {
            SignUp mSignUp = new SignUp(userName, Password, this);
            mSignUp.authenticate();

            }

        }

    @Override
    public void onSuccess() {
        mAddUserCallback.onUserAdded();
    }

    @Override
    public void onFailure() {
        mAddUserCallback.onUserNotAdded();
    }
}
