package todo_code_apps.com.todotaskreminder.managers.alarm;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;

import todo_code_apps.com.todotaskreminder.R;
import todo_code_apps.com.todotaskreminder.managers.notification.NotificationCommon;
import todo_code_apps.com.todotaskreminder.receivers.AlarmReceiver;
import todo_code_apps.com.todotaskreminder.services.ReminderService;
import todo_code_apps.com.todotaskreminder.utils.Utils;

/**
 * Created by Revanth K on 7/11/18.
 */
public class AlarmCommon {

    private static final String TAG = AlarmCommon.class.getSimpleName();
    private static final int SET_REMINDER_REQUEST_CODE = 100;

    // Below value should be same as the value in the XML
    private static final String DEFAULT_SNOOZE = "10";

    // Reminder alert  timeout - self kill after 10 minutes
    public static final int REMINDER_ALERT_TIMEOUT_SECONDS = 1 * 60;

    public static final String REMINDER_KILL_ACTION = "intent.action.reminder_kill";
    public static final String REMINDER_ALERT_ACTION = "intent.action.reminder_alert";
    public static final String REMINDER_SNOOZE_ACTION = "intent.action.reminder_snooze";
    public static final String REMINDER_CANCEL_ACTION = "intent.action.reminder_cancel";

    public static void setReminder(Context context, Date reminderDate) {

        Log.i(TAG, reminderDate.toString());

        // Create a calendar object
        Calendar c = Calendar.getInstance();
        c.setTime(reminderDate);

        setAlarm(context, c.getTimeInMillis());
    }

    public static void setReminder(Context context, long timeInMills) {

        setAlarm(context, timeInMills);
    }

    private static void setAlarm(Context context, long timeInMills) {

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if(alarmManager == null) {
            Log.e(TAG, "setAlarm : alarmManager is NULL. This is not expected. Returning " +
                    "without setting the reminder");
            return;
        }

        Intent reminderAlertIntent = new Intent(context, AlarmReceiver.class);
        reminderAlertIntent.setAction(REMINDER_ALERT_ACTION);
        PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(context, SET_REMINDER_REQUEST_CODE, reminderAlertIntent, 0);

        if (Utils.isMOrLater()) {
            // Ensure the alarm fires even if the device is dozing.
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, timeInMills, alarmPendingIntent);
        }
        else if(Utils.isKOrLater()) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, timeInMills, alarmPendingIntent);
        }
        else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, timeInMills, alarmPendingIntent);
        }

        Log.i(TAG, "Alarm set....");
    }


    public static void cancelReminder(Context context) {

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if(alarmManager == null) {
            Log.e(TAG, "cancelReminder : alarmManager is NULL. This is not expected. Returning " +
                    "without cancelling the reminder");
            return;
        }

        Intent reminderAlertIntent = new Intent(context, AlarmReceiver.class);
        reminderAlertIntent.setAction(REMINDER_ALERT_ACTION);
        PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(context, SET_REMINDER_REQUEST_CODE, reminderAlertIntent, 0);
        alarmManager.cancel(alarmPendingIntent);

        Intent serviceIntent = new Intent(context, ReminderService.class);
        serviceIntent.setAction(AlarmCommon.REMINDER_ALERT_ACTION);
        context.stopService(serviceIntent);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if(notificationManager == null) {
            Log.e(TAG, "setReminder : notificationManager is NULL. This is not expected. Returning " +
                    "without showing the notification");
            return;
        }
        notificationManager.cancel(NotificationCommon.NOTIFICATION_ID);
    }

    public static void snoozeReminder(Context context) {

        // Cancel the previous alarm
        cancelReminder(context);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        final String snooze = prefs.getString(context.getString(R.string.sp_key_snooze_time), DEFAULT_SNOOZE);
        int snoozeMinutes = Integer.parseInt(snooze);
        Log.i(TAG, String.valueOf(snoozeMinutes));
        final long snoozeTime = System.currentTimeMillis() + (1000 * 60 * snoozeMinutes);

        // Set a new reminder with snooze time
        setReminder(context, snoozeTime);
    }

}
