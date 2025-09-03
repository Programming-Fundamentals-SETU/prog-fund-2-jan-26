package utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CategoryUtility {

    private static Set<String> subjectCategories = new HashSet<>(){{
        add("Technology");
        add("Business");
        add("Language");
        add("Geography");
        add("History");
        add("Maths");
        add("General");
    }};
    public static Set<String> getSubjectCategories() {
        return subjectCategories;
    }
    public static boolean isValidSubjectCategory(String category) {
        //must not be case sensitive
        for (String subject: subjectCategories){
            if (subject.equalsIgnoreCase(category)) {
                return true;
            }
        }
       return false;
    }
    public static String getDefaultSubjectCategory(){
        return "General";
    }

  
    public static String getDefaultGameCategory(){
        return "General";
    }

    public static String getDefaultProductivityCategory(){
        return "Productivity";
    }
}
