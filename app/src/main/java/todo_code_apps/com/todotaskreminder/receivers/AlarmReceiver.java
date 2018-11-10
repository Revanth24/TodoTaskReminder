package todo_code_apps.com.todotaskreminder.receivers;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import todo_code_apps.com.todotaskreminder.R;
import todo_code_apps.com.todotaskreminder.managers.alarm.AlarmCommon;
import todo_code_apps.com.todotaskreminder.managers.notification.NotificationCommon;
import todo_code_apps.com.todotaskreminder.services.ReminderService;
import todo_code_apps.com.todotaskreminder.services.TestJobScheduler;
import todo_code_apps.com.todotaskreminder.utils.Utils;

/**
 * Created by Revanth K on 4/11/18.
 */
public class AlarmReceiver extends BroadcastReceiver {

    private static final String TAG = AlarmReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.i(TAG, "onReceive");

        if(intent.getAction().equals(AlarmCommon.REMINDER_KILL_ACTION))
        {
            Log.i(TAG, "Kill");
            NotificationCommon.updateNotification(context);
            return;
        }
        else if(intent.getAction().equals(AlarmCommon.REMINDER_SNOOZE_ACTION))
        {
            AlarmCommon.snoozeReminder(context);
            return;
        }
        else if(intent.getAction().equals(AlarmCommon.REMINDER_CANCEL_ACTION))
        {
            AlarmCommon.cancelReminder(context);
            return;
        }

        Toast.makeText(context, "Reminder", Toast.LENGTH_LONG).show();

        Intent serviceIntent = new Intent(context, ReminderService.class);
        serviceIntent.setAction(AlarmCommon.REMINDER_ALERT_ACTION);
        try {
            context.startService(serviceIntent);
        }
        catch (IllegalStateException e) {
            Log.e(TAG, "IllegalStateException during start service");

            if (Utils.isLOrLater()) {
                onServiceStartFailed(context);
            }
        }

        NotificationCommon.showNotification(context);
    }

    // If service start failed try job scheduler
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void onServiceStartFailed(Context context) {

        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        jobScheduler.schedule(new JobInfo.Builder(1, new ComponentName(context, TestJobScheduler.class))
                .setMinimumLatency(1)
                .setOverrideDeadline(1)
                .build());
    }

   }
