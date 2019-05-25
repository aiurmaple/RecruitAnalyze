package indi.aiurmaple.recruitanalyze.datadisplay.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public enum Quarter {
    SPRING("01", "03"), AUTUMN("04", "05"), ALL("01", "12");

    private List<String> quarterTime;

    Quarter(String startDate, String endDate) {
        Calendar calendar = Calendar.getInstance();
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        quarterTime = new ArrayList<>();
        quarterTime.add(year + "-" + startDate + "-01");
        quarterTime.add(year + "-" + endDate + "-31");
    }

    public List<String> getQuarterTime() {
        return quarterTime;
    }
}
