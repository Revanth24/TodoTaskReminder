package todo_code_apps.com.todotaskreminder.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Revanth K on 1/11/18.
 */
public class DateUtils {

    public static final int DAYS = 0;
    public static final int HOURS = 1;
    public static final int MINUTES = 2;
    public static final int SECONDS = 3;
    public static final int MILLISECONDS = 4;

    public static Date CreateDate(int day, int month, int year)
    {
        Calendar c = Calendar.getInstance();

        // set the calendar to start of today
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        // setup the date
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);

        // and get that as a Date
        Date resultDate = c.getTime();
        return resultDate;

    }

    public static Date CreateDate(int day, int month, int year, int hour, int minute, int seconds, int milliSecs)
    {
        Calendar c = Calendar.getInstance();

        // set the calendar to start of today
        c.set(Calendar.HOUR_OF_DAY, hour);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, seconds);
        c.set(Calendar.MILLISECOND, milliSecs);

        // setup the date
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);

        // and get that as a Date
        Date resultDate = c.getTime();
        return resultDate;

    }

    public static Date subtractDays(Date date, int days) {

        Calendar c = Calendar.getInstance();
        c.setTime(date);

        c.add(Calendar.DATE, -days);

        return c.getTime();
    }

    /**
     *
     * @param date The source date
     * @param hours No of hours to be subtracted (24 hour format)
     * @return The subtracted date
     */
    public static Date subtractHours(Date date, int hours) {

        Calendar c = Calendar.getInstance();
        c.setTime(date);

        c.add(Calendar.HOUR_OF_DAY, -hours);

        return c.getTime();
    }

    /**
     *
     * @param date The source date
     * @param minutes No of hours to be subtracted
     * @return The subtracted date
     */
    public static Date subtractMinutes(Date date, int minutes) {

        Calendar c = Calendar.getInstance();
        c.setTime(date);

        c.add(Calendar.MINUTE, -minutes);

        return c.getTime();
    }

    public static Date addMinutes(Date date, int minutes) {

        Calendar c = Calendar.getInstance();
        c.setTime(date);

        c.add(Calendar.MINUTE, minutes);

        return c.getTime();
    }

    public static Date subtractMilliSeconds(Date date, int milliSeconds) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        c.add(Calendar.MILLISECOND, -milliSeconds);

        return c.getTime();
    }

}
