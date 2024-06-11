package com.exe212.tutormind.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Common {
    public static final String SORT_ASC = "asc";
    public static final String SORT_DESC = "desc";
    public static final boolean IS_ACTIVE = true;
    public static final String USER_ACTIVE = "Active";
    public static final String DEFAULT_PAGE_SIZE = "10";
    public static final String DATETIME_FORMATTER = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMATTER = "yyyy-MM-dd";
    public static final Integer numberOfSlot = 6;

    public static String convertDayOfWeek(List<Integer> dayOfWeeks) {
        if (dayOfWeeks == null)
            dayOfWeeks = new ArrayList<Integer>();

        List<String> list = dayOfWeeks.stream().map(Object::toString).toList();

        return String.join(
                "#", list
        );
    }

    public static List<Integer> generateDayOfWeekList(String dayOfWeek) {
        if (dayOfWeek == null || dayOfWeek.equals(""))
            return null;

        return Arrays.stream(dayOfWeek.split("#"))
                .map(Integer::parseInt).toList();
    }
}
