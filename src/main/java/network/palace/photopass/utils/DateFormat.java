package network.palace.photopass.utils;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Tom
 * @since 28/12/2020
 * @version 1.0.0
 */

public class DateFormat {

    public static String formattedTime() {
        Format formatter = new SimpleDateFormat("MM-dd-yyyy");
        Date today = Calendar.getInstance().getTime();
        String s = formatter.format(today);
        return s;
    }
}
