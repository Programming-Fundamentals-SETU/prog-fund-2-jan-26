package utils;

import java.util.ArrayList;

public class CategoryUtility {

    private static ArrayList<String> categories = new ArrayList<>(){{
        add("Home");
        add("Work");
        add("Hobby");
        add("Holiday");
        add("College");
    }};

    public static ArrayList<String> getCategories() {
        return categories;
    }

    public static boolean isValidCategory(String categoryToCheck) {
        for (String category : categories) {
            if (category.equalsIgnoreCase(categoryToCheck)) {
                return true;
            }
        }
        return false;
    }

}
