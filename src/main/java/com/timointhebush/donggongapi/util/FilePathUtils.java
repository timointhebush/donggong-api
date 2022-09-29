package com.timointhebush.donggongapi.util;

import lombok.experimental.UtilityClass;

import java.nio.file.Path;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static java.util.Calendar.*;

@UtilityClass
public class FilePathUtils {

    public static Path generateCurrentDateTimePath() {
        final Calendar cal = new GregorianCalendar();
        String year = Integer.toString(cal.get(YEAR));
        String month = String.format("%02d", cal.get(MONTH) + 1);
        String day = String.format("%02d", cal.get(DAY_OF_MONTH));
        String hour = String.format("%02d", cal.get(HOUR_OF_DAY));
        String minute = String.format("%02d", cal.get(MINUTE));
        return Path.of(year, month, day, hour, minute);
    }
}
