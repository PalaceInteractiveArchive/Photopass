package network.palace.photopass.utils;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFormat {

    public static String formattedTime() {
        Format formatter = new SimpleDateFormat("MM-dd-yyyy");
        Date today = Calendar.getInstance().getTime();
        String s = formatter.format(today);
        return s;
    }
}
