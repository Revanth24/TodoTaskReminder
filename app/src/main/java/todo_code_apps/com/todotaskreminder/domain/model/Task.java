package todo_code_apps.com.todotaskreminder.domain.model;

import java.util.Date;

/**
 * Created by Revanth K on 18/10/18.
 */
public class Task {

    private long   mId;
    private String mTitle;
    private String mDescription;
    private Date mReminderDate;

    public Task(String title, String description, Date reminderDate)
    {
        mId = new Date().getTime();
        mTitle = title;
        mDescription = description;
        mReminderDate = reminderDate;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public void setReminderDate(Date reminderDate) {
        mReminderDate = reminderDate;
    }

    public long getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public Date getReminderDate() {
        return mReminderDate;
    }
}
