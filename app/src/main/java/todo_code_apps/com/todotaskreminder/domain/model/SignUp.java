package todo_code_apps.com.todotaskreminder.domain.model;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUp {

    private String UserName;
    private String Password;
    private LoginListener LoginCallback;


    public SignUp(String UserName, String Password, LoginListener mLoginCallback) {
        this.UserName = UserName;
        this.Password = Password;
        this.LoginCallback = mLoginCallback;
    }

    public void authenticate() {
        FirebaseAuth auth;
        auth = FirebaseAuth.getInstance();
        //authenticate user
        auth.createUserWithEmailAndPassword(UserName, Password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {

                        if (!task.isSuccessful()) {
                            LoginCallback.onFailure();
                        } else {
                            verifyEmail();
                        }
                    }
                });


    }

    public void verifyEmail() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (!user.isEmailVerified()) {
            user.sendEmailVerification()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(Task<Void> task) {
                            if (task.isSuccessful()) {
                                LoginCallback.onSuccess();
                            } else {

                            }
                        }
                    });
        }
    }
}








