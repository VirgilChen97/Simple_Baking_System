package tools;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Useful tools for time related actions
 * @author Yifeng Chen
 * @version 1.0
 */
public class DateTools {

    /**
     *  Get current date string
     *
     *  Format: yyyy-MM-dd
     * @return date String
     */
    public static String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(new Date());
    }

    /**
     * calculate how many days are there between given dates
     * @param str1 time 1:
     * @param str2 time 2:
     * @return days between time 1 and time 2
     */
    public static long getDistanceDays(String str1, String str2){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date one;
        Date two;
        long days=0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff ;
            if(time1<time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            long trans = 1000 * 60 * 60 ;
            long trans2 = 24;
            days = diff / trans / trans2;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }
}