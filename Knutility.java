/**
 * A collection of utility functions because I hate Java.
 * 
 * @author Antonio
 * @version 3.16.2020
 */

import java.util.Calendar;

public class Knutility  
{

    /**
     * Ensure an integer n is within the range [min, max]
     * 
     * @param  n   The number you want to be clamped
     * @param  min The minimum value the number should be
     * @param  max The maximum value the number should be
     * 
     * @return Integer n if the integer is in the range or min/max
     */
    public static int clampInteger(int n, int min, int max)
    {
        if(n < min){
            return min;
        }
        if(n > max){
            return max;
        }
        return n;
    }
    
    
    /**
     * Get number of milliseconds since UNIX epoch
     *
     * @return Number(long) of milliseconds since January 1st, 1970
     */
    public static long tick(){
        Calendar now = Calendar.getInstance();
        return now.getTimeInMillis();
    }
    
    /**
     * Convert milliseconds to a valid timestamp
     *
     * @param ms The millisecond duration
     * @return A String timestamp based on the millisecond duration
     */
    public static String formatTimestamp(long ms){
        long minutes = ms/60000;
        long seconds = (ms % 60000)/1000;
        
        return String.valueOf(minutes)+" minutes "+seconds+" seconds";
    }
}
