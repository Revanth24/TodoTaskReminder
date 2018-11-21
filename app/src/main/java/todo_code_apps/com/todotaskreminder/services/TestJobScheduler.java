package todo_code_apps.com.todotaskreminder.services;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

/**
 * Created by Revanth K on 8/11/18.
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class TestJobScheduler extends JobService {

    private static final String TAG = TestJobScheduler.class.getSimpleName();

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Log.i("TAG", "onStartJob");

        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }

}
