package todo_code_apps.com.todotaskreminder.presentation.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Calendar;

import todo_code_apps.com.todotaskreminder.R;
import todo_code_apps.com.todotaskreminder.domain.executor.impl.ThreadExecutor;
import todo_code_apps.com.todotaskreminder.managers.alarm.AlarmCommon;
import todo_code_apps.com.todotaskreminder.presentation.presenters.AddTaskPresenter;
import todo_code_apps.com.todotaskreminder.presentation.presenters.EditTaskPresenter;
import todo_code_apps.com.todotaskreminder.presentation.presenters.impl.AddTaskPresenterImpl;
import todo_code_apps.com.todotaskreminder.presentation.presenters.impl.EditTaskPresenterImpl;
import todo_code_apps.com.todotaskreminder.receivers.AlarmReceiver;
import todo_code_apps.com.todotaskreminder.threading.impl.MainThreadImpl;
import todo_code_apps.com.todotaskreminder.utils.DateUtils;

public class MainActivity extends AppCompatActivity implements AddTaskPresenter.View, EditTaskPresenter.View{

    private static final String TAG = AlarmReceiver.class.getSimpleName();

    private AddTaskPresenter mPresenter;
    private EditTaskPresenter mEditPresenter;
    private LinearLayout linearLayoutProgress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        final Context context = this;
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        linearLayoutProgress = findViewById(R.id.linlaHeaderProgress);

        mPresenter = new AddTaskPresenterImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);

        mEditPresenter = new EditTaskPresenterImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);

        mPresenter.addNewTask("Title1", "Adding my First task", Calendar.getInstance().getTime(), Calendar.getInstance().getTime());

        mEditPresenter.editTask("Title1", "Adding my First task", Calendar.getInstance().getTime(), Calendar.getInstance().getTime());

        Button cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Reminder Cancelled", Toast.LENGTH_LONG).show();
                AlarmCommon.cancelReminder(context);
            }
        });

        Button setButton = findViewById(R.id.setButton);
        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Reminder set for 1 minute from now...", Toast.LENGTH_LONG).show();
                AlarmCommon.setReminder(context, DateUtils.addMinutes(Calendar.getInstance().getTime(), 1));
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_settings:
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
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
