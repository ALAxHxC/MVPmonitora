package ponny.org.monitora.utils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {


    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    private static final SimpleDateFormat sdf_pretty = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    private static final SimpleDateFormat sdf_show = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public static String getPrettyDateNow() {
        Date date = new Date();
        String pretty = sdf.format(date);
        return pretty;
    }

    public static String getPrettyDateFromString(String date_string) {
        String pretty = date_string;
        try {
            Date date = sdf_pretty.parse(date_string);
            pretty = sdf_show.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return pretty;
    }
}
