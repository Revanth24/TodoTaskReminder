package todo_code_apps.com.todotaskreminder.presentation.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import todo_code_apps.com.todotaskreminder.R;
import todo_code_apps.com.todotaskreminder.domain.executor.impl.ThreadExecutor;
import todo_code_apps.com.todotaskreminder.presentation.presenters.ValidateLogin;
import todo_code_apps.com.todotaskreminder.presentation.presenters.impl.ValidateLoginImpl;
import todo_code_apps.com.todotaskreminder.threading.impl.MainThreadImpl;



public class LoginActivity extends AppCompatActivity implements ValidateLogin.View {
    //UI Declarations
    private EditText userName;
    private EditText Password;
    private Button loginbutton;
    private Button signup;
    private SignInButton GoogleLogin;
    private LinearLayout linearLayoutProgress;

    //Presenter Declarations
    private ValidateLogin mLoginPresenter;

    private GoogleApiClient mGoogleApiClient;
    public String WEBCLIENTID="460159338325-j8phgsk7p0pdjpucn27jodvm3ce0tnap.apps.googleusercontent.com";
    private static final int REQUEST_CODE_SIGN_IN = 1234;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.loginactivity);
        initViews();
        GoogleSignInFirebase();

        loginbutton.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                mLoginPresenter = new ValidateLoginImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(),LoginActivity.this);
                String mUserName = userName.getText().toString();
                String mPassword = Password.getText().toString();
                mLoginPresenter.LoginVerify(mUserName,mPassword,true);
            }
        });

        GoogleLogin.setOnClickListener(new android.view.View.OnClickListener(){

            @Override
            public void onClick(android.view.View v) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(intent, REQUEST_CODE_SIGN_IN);
            }
        });
    }

    private void GoogleSignInFirebase()
    {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(WEBCLIENTID)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this,new GoogleApiClient.OnConnectionFailedListener(){
            @Override
            public void onConnectionFailed(ConnectionResult connectionResult) {

            }
        })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    private void initViews() {
        userName =(EditText)findViewById(R.id.input_email);
        Password =(EditText)findViewById(R.id.input_password);
        loginbutton =(Button) findViewById(R.id.btn_login);
        GoogleLogin =(SignInButton) findViewById(R.id.btn_sign_in);
        signup = (Button)findViewById(R.id.link_signup);
        linearLayoutProgress = findViewById(R.id.linlaHeaderProgress);
    }

    public void perform_action(View v) {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    public void Forget_password(View v){
        Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
        startActivity(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent();
        if (requestCode == REQUEST_CODE_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            mLoginPresenter = new ValidateLoginImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(),LoginActivity.this);
            mLoginPresenter.GoogleLogin(result);
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Toast.makeText(this, currentUser.getEmail(), Toast.LENGTH_LONG).show();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void LoginDone() {
        Toast.makeText(this,"Done", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void UsernameError() {
        userName.setError("Enter the Valid Email");
    }

    @Override
    public void PasswordError() {
        Password.setError("Password length should be more then 6 characters");
    }

    @Override
    public void LoginError() {
        Toast.makeText(this,"Email or Password Entered is incorrect", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgress() {
        linearLayoutProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() { linearLayoutProgress.setVisibility(View.GONE); }

    @Override
    public void showError(String message) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
