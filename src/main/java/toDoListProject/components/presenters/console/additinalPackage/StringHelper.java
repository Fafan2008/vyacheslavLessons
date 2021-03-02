package toDoListProject.components.presenters.console.additinalPackage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class StringHelper {
    static public boolean ToBoolean(String input) throws IllegalArgumentException {
        if (input == null)
            throw new IllegalArgumentException("Null input for stringToBoolean");
        input = input.trim().toLowerCase();
        if (input.equals("1") || input.equals("yes") || input.equals("y") || input.equals("true") || input.equals("t"))
            return true;
        if (input.equals("0") || input.equals("no")|| input.equals("n") || input.equals("false") || input.equals("f"))
            return false;
        throw new IllegalArgumentException("Bad input for stringToBoolean: " + input);
    }
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
    public static String quote(String s) {
        return new StringBuilder()
                .append('\'')
                .append(s)
                .append('\'')
                .toString();
    }
    public static Date toDate(String s) {
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", new Locale("en_US"));
        Date date = null;
        try {
            date = formatter.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
