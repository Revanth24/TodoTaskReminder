package todo_code_apps.com.todotaskreminder.domain.model;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class GoogleLogin {
    private FirebaseAuth mAuth;
    private LoginListener LoginCallback;

    public GoogleLogin(LoginListener mLoginCallback)
    {
        LoginCallback = mLoginCallback;
    }

    public void firebaseAuthWithGoogle(GoogleSignInAccount acct) {

        mAuth = FirebaseAuth.getInstance();
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success
                            LoginCallback.onSuccess();
                        } else {
                            // Sign in fails
                            LoginCallback.onFailure();
                        }

                    }
                });
    }
}

