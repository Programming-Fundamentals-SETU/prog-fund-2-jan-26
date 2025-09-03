package utils;

import java.util.HashMap;
import java.util.Map;

public class LicenseUtility {

    //https://en.wikipedia.org/wiki/Software_license
    private static Map<String, String> licenses = new HashMap<>(){{
        put("Public Domain"      , "Grants all rights");
        put("Permissive"         , "Grants use rights, including right to relicense (allows proprietization, license compatibility)");
        put("Copyleft Protective", "Grants use rights, forbids proprietization");
        put("Noncommercial"      , "Grants rights for noncommercial use only. May be combined with copyleft.");
        put("Proprietary"        , "Traditional use of copyright; no rights need be granted");
        put("Trade secret"       , "No information made public");
    }};

    public static Map<String, String> getLicenses(){
        return licenses;
    }

    public static String getLicenseKeys(){
        return licenses.keySet().toString();
    }

    public static String getLicense(String license){
        return licenses.get(license);
    }

    public static boolean isValidLicense(String license){
        //must not be case sensitive
        for (Map.Entry<String, String> entry : licenses.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(license)){
                return true;
            }
        }
        return false;
    }

    public static String getDefaultLicense(){
        return "Proprietary";
    }

}
