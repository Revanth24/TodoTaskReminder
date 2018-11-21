package todo_code_apps.com.todotaskreminder.presentation.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import todo_code_apps.com.todotaskreminder.R;
import todo_code_apps.com.todotaskreminder.managers.preference.PreferenceFragment;

/**
 * Created by Revanth K on 8/11/18.
 */
public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new PreferenceFragment()).commit();
    }
}
