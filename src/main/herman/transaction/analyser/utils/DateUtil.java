package herman.transaction.analyser.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private static final SimpleDateFormat DATA_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public static Date convertToDate(String date) throws ParseException {
        return DATA_DATE_FORMAT.parse(date);
    }

    public static boolean isDateBetween(Date date, Date start, Date end) {
        return date.compareTo(start) >= 0 && date.compareTo(end) <= 0;
    }
}
