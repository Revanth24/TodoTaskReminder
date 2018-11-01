package todo_code_apps.com.todotaskreminder.domain.model;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Login{

    private String UserName;
    private String Password;
    private LoginListener LoginCallback;


    public Login(String UserName, String Password,LoginListener  mLoginCallback)
    {
        this.UserName = UserName;
        this.Password = Password;
        LoginCallback= mLoginCallback;
    }

    public void authenticate() {
        FirebaseAuth auth;
        auth = FirebaseAuth.getInstance();
        //authenticate user
        auth.signInWithEmailAndPassword(UserName, Password)
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (task.isSuccessful()) {
                            LoginCallback.onSuccess();
                        }
                        else{
                            LoginCallback.onFailure();
                        }

                    }
                });


        }

        }





