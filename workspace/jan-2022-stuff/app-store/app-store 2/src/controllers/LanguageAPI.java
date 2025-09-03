package controllers;

import persistence.PersistenceManager;
import persistence.Serializer;

import java.util.HashSet;
import java.util.Set;

//todo consider getting rid of this one as it could confuse them...e.g. why has language got an api where as category and licence doesn't
public class LanguageAPI implements PersistenceManager {

    private Set<String> languages = new HashSet<String>();

    private Serializer serializer;

    public LanguageAPI(Serializer serializer)  {
        languages.add("English");   //default language - cannot be deleted
        this.serializer = serializer;
    }

    public boolean addLanguage(String language){
        return languages.add(language);
    }

    public boolean deleteLanguage(String language)  {
        if (language.equalsIgnoreCase("english"))
            return false;
        else
            return languages.remove(language);
    }

    public String getLanguages(){
        return languages.toString();
    }

    public boolean isValidLanguage(String language){
        return languages.contains(language);
    }

    public String getDefaultLanguage(){
        return "English";
    }

    @SuppressWarnings("unchecked")
    public void load() throws Exception {
        languages = (Set<String>) serializer.read();
    }

    public void store() throws Exception {
        serializer.write(languages);
    }

}
