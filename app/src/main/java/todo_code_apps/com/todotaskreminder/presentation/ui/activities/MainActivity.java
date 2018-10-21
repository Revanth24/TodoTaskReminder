package todo_code_apps.com.todotaskreminder.presentation.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Calendar;

import todo_code_apps.com.todotaskreminder.R;
import todo_code_apps.com.todotaskreminder.domain.executor.impl.ThreadExecutor;
import todo_code_apps.com.todotaskreminder.domain.model.Task;
import todo_code_apps.com.todotaskreminder.presentation.presenters.AddTaskPresenter;
import todo_code_apps.com.todotaskreminder.presentation.presenters.EditTaskPresenter;
import todo_code_apps.com.todotaskreminder.presentation.presenters.impl.AddTaskPresenterImpl;
import todo_code_apps.com.todotaskreminder.presentation.presenters.impl.EditTaskPresenterImpl;
import todo_code_apps.com.todotaskreminder.threading.impl.MainThreadImpl;

public class MainActivity extends AppCompatActivity implements AddTaskPresenter.View, EditTaskPresenter.View{

    private AddTaskPresenter mPresenter;
    private EditTaskPresenter mEditPresenter;
    LinearLayout linearLayoutProgress;
    private Task mEditedTask;

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.pause();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activitymain);

        linearLayoutProgress = findViewById(R.id.linlaHeaderProgress);

        mPresenter = new AddTaskPresenterImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);

        mEditPresenter = new EditTaskPresenterImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);

        mPresenter.addNewTask("Title1", "Adding my First task", Calendar.getInstance().getTime(), Calendar.getInstance().getTime());

        mEditPresenter.editTask("Title1", "Adding my First task", Calendar.getInstance().getTime(), Calendar.getInstance().getTime());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    public void onTaskAdded() {
        Toast.makeText(getApplicationContext(), "Task Added successfully", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgress() {
        linearLayoutProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        linearLayoutProgress.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onTaskUpdated() {

    }
}
