package indi.aiurmaple.recruitanalyze.datadisplay.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
    public static String getCurDate() {
        Date curDate = new Date(System.currentTimeMillis());
        return SDF.format(curDate);
    }

    public static String getBeforeDate() {
        Date curDate = new Date(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(curDate);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date beforeDate = calendar.getTime();
        return SDF.format(beforeDate);
    }

    public static int getMonth() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.MONTH) + 1;
    }

    public static int getYear() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.YEAR);
    }
}
