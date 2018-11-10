package todo_code_apps.com.todotaskreminder.managers.preference;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import todo_code_apps.com.todotaskreminder.R;

/**
 * Created by Revanth K on 8/11/18.
 */
public class PreferenceFragment extends PreferenceFragmentCompat {

    private static final String TAG = PreferenceFragment.class.getSimpleName();

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {

        addPreferencesFromResource(R.xml.preferences);
    }
}
