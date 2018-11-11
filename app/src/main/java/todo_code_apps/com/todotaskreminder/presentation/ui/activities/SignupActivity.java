package todo_code_apps.com.todotaskreminder.presentation.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import todo_code_apps.com.todotaskreminder.R;
import todo_code_apps.com.todotaskreminder.domain.executor.impl.ThreadExecutor;
import todo_code_apps.com.todotaskreminder.presentation.presenters.ValidateLogin;
import todo_code_apps.com.todotaskreminder.presentation.presenters.impl.ValidateLoginImpl;
import todo_code_apps.com.todotaskreminder.threading.impl.MainThreadImpl;

public class SignupActivity extends AppCompatActivity implements ValidateLogin.View {
    private EditText userName;
    private EditText Password;
    private Button signupButton;
    private ValidateLogin mPresenter;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signupactivity);
        initViews();

        signupButton.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                mPresenter = new ValidateLoginImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(),SignupActivity.this);
                String mUserName = userName.getText().toString();
                String mPassword = Password.getText().toString();
                mPresenter.LoginVerify(mUserName,mPassword,false);
            }
        });
    }


    private void initViews() {

        userName =(EditText)findViewById(R.id.input_email);
        Password =(EditText)findViewById(R.id.input_password);
        signupButton =(Button) findViewById(R.id.btn_signup);
    }
    @Override
    public void LoginDone() {
        Toast.makeText(this,"Done", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(SignupActivity.this, MainActivity.class);
        startActivity(intent);
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

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String message) {

    }
}
