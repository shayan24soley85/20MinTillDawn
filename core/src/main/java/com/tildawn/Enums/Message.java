package com.tildawn.Enums;

import com.tildawn.Main;
import com.tildawn.Model.Language.Language;
import com.tildawn.Model.Language.LanguageSetting;

public enum Message {
    PLEASE_FILL_ALL_FIELDS("Please fill all the fields", "Veuillez remplir tous les champs"),
    PASSWORDS_DO_NOT_MATCH("Passwords do not match!", "Les mots de passe ne correspondent pas !"),
    WEAK_PASSWORD("You must enter a strong password!", "Vous devez entrer un mot de passe fort !"),
    USERNAME_ALREADY_IN_USE("This username is already in use!", "Ce nom d'utilisateur est déjà utilisé !"),
    USER_NOT_FOUND("User not found", "Utilisateur non trouvé"),
    WRONG_PASSWORD("Wrong password", "Mot de passe incorrect"),
    LOGOUT_SUCCESS("You have successfully logged out \n you will return to login menu in few seconds!",
        "Vous vous êtes déconnecté avec succès\nvous serez redirigé vers le menu de connexion dans quelques secondes !"),
    LOGIN_FIRST("You have to login first", "Vous devez d'abord vous connecter"),
    ACCOUNT_DELETED("You have successfully deleted your account \n you will return to the login menu in few seconds",
        "Votre compte a été supprimé avec succès\nvous serez redirigé vers le menu de connexion dans quelques secondes"),
    USERNAME_CHANGED("You have successfully changed your username", "Vous avez changé votre nom d'utilisateur avec succès"),
    PASSWORD_CHANGED("You successfully changed password", "Vous avez changé votre mot de passe avec succès"),
    SECURITY_ANSWER_WRONG("Security answer doesn't match", "La réponse de sécurité ne correspond pas"),
    AVATAR_NOT_CHOSEN("You have not choose an avatar yet", "Vous n'avez pas encore choisi d'avatar"),
    AVATAR_CHANGED("You have successfully changed your avatar", "Vous avez changé votre avatar avec succès"),
    CANT_SHOOT_WHILE_RELOADING("You can't shoot while reloading!", "Vous ne pouvez pas tirer pendant le rechargement!"),
    RELOADING("Reloading!", "Rechargement!"),
    NO_AMMO("You don't have enough ammo", "Vous n'avez pas assez de munitions");

    private final String english;
    private final String french;

    Message(String english, String french) {
        this.english = english;
        this.french = french;
    }

    public String getText() {
        return Main.getMain().getApp().getCurrentLanguage().getCurrentLanguage() == Language.English ? english : french;
    }

    @Override
    public String toString() {
        return getText();
    }
}
