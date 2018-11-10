package todo_code_apps.com.todotaskreminder.managers.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import todo_code_apps.com.todotaskreminder.R;
import todo_code_apps.com.todotaskreminder.managers.alarm.AlarmCommon;
import todo_code_apps.com.todotaskreminder.receivers.AlarmReceiver;
import todo_code_apps.com.todotaskreminder.utils.Utils;

/**
 * Created by Revanth K on 10/11/18.
 */
public class NotificationCommon {

    private static final String TAG = NotificationCommon.class.getSimpleName();

    public static final int NOTIFICATION_ID = 1;

    public static void showNotification(Context context)
    {

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if(notificationManager == null) {
            Log.e(TAG, "showNotification : notificationManager is NULL. This is not expected. Returning " +
                    "without showing the notification");
            return;
        }

        // Snooze pending intent
        Intent snoozeIntent = new Intent(context, AlarmReceiver.class);
        snoozeIntent.setAction(AlarmCommon.REMINDER_SNOOZE_ACTION);
        PendingIntent snoozePendingIntent = PendingIntent.getBroadcast(context, 0, snoozeIntent, 0);

        // Cancel pending intent
        Intent cancelIntent = new Intent(context, AlarmReceiver.class);
        cancelIntent.setAction(AlarmCommon.REMINDER_CANCEL_ACTION);
        PendingIntent cancelPendingIntent = PendingIntent.getBroadcast(context, 0, cancelIntent, 0);

        // Build the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, null)
                .addAction(0, "SNOOZE", snoozePendingIntent)
                .addAction(0, "CANCEL", cancelPendingIntent)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Task is due....")
                .setContentText("Blah Blah task needs to be completed")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setCategory(NotificationCompat.CATEGORY_REMINDER)
                .setVisibility(NotificationCompat.VISIBILITY_PRIVATE);

        // Need to create Notification channel for Oreo and later devices
        if (Utils.isOOrLater()) {

            String CHANNEL_ID = "channel_id_01";
            CharSequence name = "Channel_name_01";
            String description = "Blah Blah task needs to be completed ..........";
            NotificationChannel channel  = new NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(description);
            builder.setChannelId(CHANNEL_ID);

            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            notificationManager.createNotificationChannel(channel);
        }
        else {

        }

        notificationManager.notify(NotificationCommon.NOTIFICATION_ID, builder.build());
    }

    public static void updateNotification(Context context) {

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if(notificationManager == null) {
            Log.e(TAG, "showNotification : notificationManager is NULL. This is not expected. Returning " +
                    "without showing the notification");
            return;
        }

        // Build the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, null)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Task is due....")
                .setContentText("Task Reminder timed out....")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setCategory(NotificationCompat.CATEGORY_REMINDER)
                .setVisibility(NotificationCompat.VISIBILITY_PRIVATE);


        // Need to create Notification channel for Oreo and later devices
        if (Utils.isOOrLater()) {

            String CHANNEL_ID = "channel_id_01";
            CharSequence name = "Channel_name_01";
            String description = "Blah Blah task needs to be completed ..........";
            NotificationChannel channel  = new NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(description);
            builder.setChannelId(CHANNEL_ID);

            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            notificationManager.createNotificationChannel(channel);
        }
        else {

        }

        notificationManager.cancel(NotificationCommon.NOTIFICATION_ID);

        notificationManager.notify(NotificationCommon.NOTIFICATION_ID, builder.build());
    }


}
