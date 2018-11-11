package ponny.org.monitora.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {


   private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    public static String getPrettyDateNow(){
        Date date=new Date();
        String pretty =  sdf.format(date);
        return pretty;
    }
}
