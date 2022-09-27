package Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class TimeOfLesson {
    public static String acceptableString(String string)
    {
        String newString;
        if (string.length() == 4)
        {
            newString = string.substring(0,1);
        }
        else
        {
            newString = string.substring(0,2);
        }
        return newString;
    }
    public static int durationOfLesson (String a, String b) throws Exception {
        String startingTime = TimeOfLesson.acceptableString(a);
        String endingTime = TimeOfLesson.acceptableString(b);
        System.out.println(startingTime + "\n" + endingTime);
        int duration = (Integer.valueOf(endingTime) - Integer.valueOf(startingTime));
        if (duration < 0)
        {
            throw new Exception("Time can not be negative");
        }
        return duration;
    }
    public static boolean compareTwoTimelines(String a, String b, String c, String d) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        Date startingTime1 = simpleDateFormat.parse(a);
        Date endingTime1 = simpleDateFormat.parse(b);
        Date startingTime2 = simpleDateFormat.parse(c);
        Date endingTime2 = simpleDateFormat.parse(d);
        if ((startingTime2.getTime() >= startingTime1.getTime()) && (endingTime2.getTime() <= endingTime1.getTime())) {
            System.out.println("A");
        return false;}
        if (((startingTime2.getTime() >= startingTime1.getTime())  && (endingTime2.getTime() >= endingTime1.getTime())) && (startingTime2.getTime() <= endingTime1.getTime())) {
            System.out.println("B");
            return false;}
        if (((startingTime1.getTime() >= startingTime2.getTime()) && (endingTime2.getTime() <= endingTime1.getTime())) && (endingTime2.getTime() >= startingTime1.getTime())) {
            System.out.println("C");
            return false;
        }
        return true;
    }
}
