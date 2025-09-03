package utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CategoryUtility {

    //TODO Nothing!  This utility is completed!

    //---------------------------------------
    //  SUBJECT CATEGORIES
    //---------------------------------------
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

    //---------------------------------------
    //  GAME CATEGORIES
    //---------------------------------------
    private static Set<String> gameCategories = new HashSet<>(Arrays.asList("Action", "Puzzle", "Card", "Strategy", "Trivia", "General"));
    public static Set<String> getGameCategories() {
        return gameCategories;
    }
    public static boolean isValidGameCategory(String category) {
        return gameCategories.contains(category);
    }
    public static String getDefaultGameCategory(){
        return "General";
    }

    //---------------------------------------
    //  PRODUCTIVITY CATEGORIES
    //---------------------------------------
    private static Set<String> productivityCategories = new HashSet<>(){{
        add("Mail");
        add("ToDoList");
        add("Notes");
        add("Messages");
        add("Calendar");
        add("Productivity");
    }};
    public static Set<String> getProductivityCategories() {
        return productivityCategories;
    }
    public static boolean isValidProductivityCategory(String category) {
        return productivityCategories.contains(category);
    }
    public static String getDefaultProductivityCategory(){
        return "Productivity";
    }
}
