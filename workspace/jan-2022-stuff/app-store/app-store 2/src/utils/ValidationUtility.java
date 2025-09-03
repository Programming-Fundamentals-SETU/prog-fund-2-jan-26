package utils;

import java.util.List;

public class ValidationUtility {

    //max 30 chars

    //format xx.xx down to x.x

    public static boolean isValidRange(int min, int max, int value){
        return ((value >= min) && (value <= max));
    }

    public static boolean isValidIndex(List list, int indexToCheck){
        return ((indexToCheck >= 0) && (indexToCheck < list.size()));
    }

    public static boolean charToBoolean(char charToConvert){
         return    ((charToConvert == 'Y')
                 || (charToConvert == 'y')
                 || (charToConvert == 'N')
                 || (charToConvert == 'n'));
    }


}
