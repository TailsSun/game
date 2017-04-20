package utils;

/**
 * Created by DNS on 10.04.2017.
 */
public class Time {
    public static final long SEKOND = 1_000_000_000l;
    public static long getTime(){
        return System.nanoTime();
    }
}
