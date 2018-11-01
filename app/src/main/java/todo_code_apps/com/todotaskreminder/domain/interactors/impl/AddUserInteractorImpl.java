package todo_code_apps.com.todotaskreminder.domain.interactors.impl;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import todo_code_apps.com.todotaskreminder.domain.executor.base.Executor;
import todo_code_apps.com.todotaskreminder.domain.interactors.AddUserInteractor;
import todo_code_apps.com.todotaskreminder.domain.interactors.base.AbstractInteractor;
import todo_code_apps.com.todotaskreminder.domain.model.Login;
import todo_code_apps.com.todotaskreminder.domain.model.LoginListener;
import todo_code_apps.com.todotaskreminder.threading.MainThread;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

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


    public AddUserInteractorImpl(Executor threadExecutor, MainThread mainThread,AddTaskCallback addTaskCallback,String userName,String password) {
        super(threadExecutor, mainThread);
        this.userName = userName;
        this.Password = password;
        mAddTastCallback = addTaskCallback;

    }

    @Override
    public void run() {
        Login mLogin =new Login(userName,Password,this);
        mLogin.authenticate();
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
