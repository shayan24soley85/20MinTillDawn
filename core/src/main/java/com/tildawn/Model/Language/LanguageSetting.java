package com.tildawn.Model.Language;

public class LanguageSetting {
    private  Language currentLanguage = Language.English;

    public   void setCurrentLanguage(Language lang) {
        currentLanguage = lang;
    }

    public  Language getCurrentLanguage() {
        return currentLanguage;
    }
}
