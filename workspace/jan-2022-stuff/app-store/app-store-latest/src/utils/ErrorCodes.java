package utils;

import java.util.HashMap;
import java.util.Map;

//todo maybe use something like this as an example of a Map in Shop???  Or add a different map?

public class ErrorCodes {

    private static Map<Integer, String> errors = new HashMap<>(){{
        put(1, "Success");
        put(2, "Not Successful");
        put(3, "Invalid Option");
        put(4, "Not Found");
    }};

    public static String getErrorMessage(Integer errorCode){
        return errors.get(errorCode);
    }

}
