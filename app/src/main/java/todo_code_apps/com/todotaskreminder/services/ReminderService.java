package todo_code_apps.com.todotaskreminder.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Vibrator;
import android.support.annotation.Nullable;

import todo_code_apps.com.todotaskreminder.managers.alarm.AlarmCommon;
import todo_code_apps.com.todotaskreminder.receivers.AlarmReceiver;

/**
 * Created by Revanth K on 7/11/18.
 */
public class ReminderService extends Service {

    private Vibrator mVibrator;

    private static final long[] sVibratePattern = new long[] { 500, 500 };

    private static final int KILLER = 1000;

    private Handler mHandler = new Handler() {

        public void handleMessage(Message message) {
            switch (message.what) {
                case KILLER:
                    // Self kill the reminder
                    killReminder();
                    stopSelf();
                    break;
            }
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mVibrator = (Vibrator)getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        play();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stop();
    }

    private void play() {

        mVibrator.vibrate(sVibratePattern, 0);
        enableKiller();
    }

    private void stop() {

        mVibrator.cancel();
        disableKiller();
    }

    private void killReminder() {
        Intent killerIntent = new Intent(ReminderService.this, AlarmReceiver.class);
        killerIntent.setAction(AlarmCommon.REMINDER_KILL_ACTION);
        sendBroadcast(killerIntent);
    }

    private void enableKiller() {
        mHandler.sendMessageDelayed(mHandler.obtainMessage(KILLER),
                1000 * AlarmCommon.REMINDER_ALERT_TIMEOUT_SECONDS);
    }

    private void disableKiller() {
        mHandler.removeMessages(KILLER);
    }


}
