package utils;

/**
 * Created by Стас on 7/2/2017.
 */
public class Log {

    public static long d(String event) {
        long time = System.currentTimeMillis();
        System.out.println(event+ " " + time);
        return time;
    }

    public static void d(String event, long startTime) {
        long time = System.currentTimeMillis();
        System.out.println(event+ " " + System.currentTimeMillis());
        System.out.println("OverallTime " + (time - startTime));
    }



}
