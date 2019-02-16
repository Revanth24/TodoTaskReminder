package todo_code_apps.com.todotaskreminder.presentation.ui.activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import todo_code_apps.com.todotaskreminder.R;
import todo_code_apps.com.todotaskreminder.domain.executor.impl.ThreadExecutor;
import todo_code_apps.com.todotaskreminder.managers.alarm.AlarmCommon;
import todo_code_apps.com.todotaskreminder.presentation.presenters.AddTaskPresenter;
import todo_code_apps.com.todotaskreminder.presentation.presenters.EditTaskPresenter;
import todo_code_apps.com.todotaskreminder.presentation.presenters.impl.AddTaskPresenterImpl;
import todo_code_apps.com.todotaskreminder.presentation.presenters.impl.EditTaskPresenterImpl;
import todo_code_apps.com.todotaskreminder.threading.impl.MainThreadImpl;
import todo_code_apps.com.todotaskreminder.utils.DateUtils;

public class MainActivity extends AppCompatActivity implements AddTaskPresenter.View, EditTaskPresenter.View{

    private static final String TAG = MainActivity.class.getSimpleName();

    private AddTaskPresenter mPresenter;
    private EditTaskPresenter mEditPresenter;
    private LinearLayout linearLayoutProgress;
    private Context mContext;
    EditText mReminderDateEditText;
    EditText mReminderTimeEditText;

    private Date mReminderDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mContext = this;

        linearLayoutProgress = findViewById(R.id.linlaHeaderProgress);

        mPresenter = new AddTaskPresenterImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);

        mEditPresenter = new EditTaskPresenterImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);

        mPresenter.addNewTask("Title1", "Adding my First task", Calendar.getInstance().getTime(), Calendar.getInstance().getTime());

        mEditPresenter.editTask("Title1", "Adding my First task", Calendar.getInstance().getTime(), Calendar.getInstance().getTime());

        Button cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Reminder Cancelled", Toast.LENGTH_LONG).show();
                AlarmCommon.cancelReminder(mContext);
            }
        });

        Button setButton = findViewById(R.id.setButton);
        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Reminder set for 1 minute from now...", Toast.LENGTH_LONG).show();
                AlarmCommon.setReminder(mContext, mReminderDate);
                Log.i(TAG, mReminderDate.toString());
            }
        });

        mReminderDateEditText = findViewById(R.id.editText);
        mReminderDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                showDatePicker(mContext, c.getTime());
            }
        });


        mReminderTimeEditText = findViewById(R.id.editText1);
        mReminderTimeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                showTimePicker(mContext, c.getTime());
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

    public void showDatePicker(Context context, Date date) {

        Calendar c = Calendar.getInstance();
        c.setTime(date);

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                mReminderDateEditText.setText(year + "-" + (month + 1) + "-" + day);
                mReminderDate = DateUtils.CreateDate(year, month, day);
            }
        }, year, month, day);

        datePickerDialog.show();

    }

    public void showTimePicker(Context context, Date date) {

        Calendar c = Calendar.getInstance();
        c.setTime(date);

        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                mReminderTimeEditText.setText(hour + "-" + (minute));
                mReminderDate = DateUtils.setTime(mReminderDate, hour, minute, 0, 0);

            }
        }, hour, minute, false);

        timePickerDialog.show();

    }
}
