package todo_code_apps.com.todotaskreminder.domain.interactors.impl;



import todo_code_apps.com.todotaskreminder.domain.executor.base.Executor;
import todo_code_apps.com.todotaskreminder.domain.interactors.AddUserInteractor;
import todo_code_apps.com.todotaskreminder.domain.interactors.base.AbstractInteractor;
import todo_code_apps.com.todotaskreminder.domain.model.GoogleLogin;
import todo_code_apps.com.todotaskreminder.domain.model.Login;
import todo_code_apps.com.todotaskreminder.domain.model.LoginListener;
import todo_code_apps.com.todotaskreminder.domain.model.Signup;
import todo_code_apps.com.todotaskreminder.threading.MainThread;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;

public class AddUserInteractorImpl extends AbstractInteractor implements AddUserInteractor,LoginListener {
    private AddUserInteractor.AddTaskCallback mAddTastCallback;
    /**
     * Constructor of this class which will instantiate the mMainThread and mThreadExecutor variables
     *
     * @param threadExecutor instance of thread executor
     * @param mainThread     instance of main thread
     */
    String userName;
    String Password;
    boolean login;
    private GoogleSignInResult result;


    public AddUserInteractorImpl(Executor threadExecutor, MainThread mainThread,AddTaskCallback addTaskCallback,String userName,String password,boolean login,GoogleSignInResult result) {
        super(threadExecutor, mainThread);
        this.userName = userName;
        this.Password = password;
        mAddTastCallback = addTaskCallback;
        this.login = login;
        this.result= result;

    }

    @Override
    public void run() {
        if(result!=null)
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
            Signup mSignup = new Signup(userName, Password, this);
            mSignup.authenticate();

            }

        }

    @Override
    public void onSuccess() {
        mAddTastCallback.onUserAdded();
    }

    @Override
    public void onFailure() {
        mAddTastCallback.onUserNotAdded();
    }
}
