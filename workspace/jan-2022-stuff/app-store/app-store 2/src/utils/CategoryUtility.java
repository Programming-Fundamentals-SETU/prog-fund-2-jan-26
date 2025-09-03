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

    private static Set<String> gameCategories = new HashSet<>(Arrays.asList("Action", "Puzzle", "Card", "Strategy", "Trivia"));
    public Set<String> getGameCategories() {
        return gameCategories;
    }
    public static boolean isValidGameCategory(String category) {
        return gameCategories.contains(category);
    }

    private static Set<String> productivityCategories = new HashSet<>(){{
        add("Mail");
        add("ToDoList");
        add("Notes");
        add("Messages");
        add("Calendar");
    }};
    public static Set<String> getProductivityCategories() {
        return productivityCategories;
    }
    public static boolean isValidProductivityCategory(String category) {
        return productivityCategories.contains(category);
    }

}
