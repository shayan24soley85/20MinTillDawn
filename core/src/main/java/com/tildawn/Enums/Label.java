package com.tildawn.Enums;

import com.tildawn.Main;
import com.tildawn.Model.Language.Language;

public enum Label {
    BACK("Back", "Retour"),
    CONFIRM("Confirm", "Confirmer"),
    CUSTOM_AVATAR("Custom Avatar", "Avatar personnalisé"),
    USERNAME("Username:", "Nom d'utilisateur :"),
    SCORE("Score:", "Score :"),
    SELECT_YOUR_AVATAR("Select Your Avatar", "Sélectionnez votre avatar"),
    PREVIEW("Preview:", "Aperçu :"),
    SIGN_UP("SignUp", "S'inscrire"),
    RECOVERY_PASSWORD("Recovery password", "Récupération du mot de passe"),
    LOGIN("Login", "Connexion"),
    LOGIN_MENU("LOGIN MENU", "MENU DE CONNEXION"),
    PASSWORD("Password:", "Mot de passe :"),
    DELETE_ACCOUNT("Delete Account", "Supprimer le compte"),
    KEYBOARD_SETTING("Keyboard Setting", "Paramètres du clavier"),
    AUTO_RELOAD("Auto Reload", "Rechargement automatique"),
    GRAYSCALE_MODE("Grayscale Mode", "Mode niveaux de gris"),
    CONTINUE_GAME("Continue Game", "Continuer le jeu"),
    SETTINGS("Settings", "Paramètres"),
    PROFILE("Profile", "Profil"),
    PRE_GAME_MENU("Pre-Game Menu", "Menu avant jeu"),
    SCOREBOARD("Scoreboard", "Tableau des scores"),
    HINT_MENU("Hint Menu", "Menu des indices"),
    LOGOUT_LOGIN_MENU("Logout/Login menu", "Menu Déconnexion/Connexion"),
    CHANGE_USERNAME("Change Username", "Changer le nom d'utilisateur"),
    CHANGE_PASSWORD("Change Password", "Changer le mot de passe"),
    CHANGE_AVATAR("Change Avatar", "Changer d'avatar"),
    SUBMIT("Submit", "Soumettre"),
    NEW_PASSWORD("New Password:", "Nouveau mot de passe :"),
    CONFIRM_PASSWORD("Confirm Password:", "Confirmer le mot de passe :"),
    SECURITY_ANSWER("Security Answer:", "Réponse de sécurité :"),
    GAME_LANGUAGE("Game language:", "Langue du jeu :"),
    BACKGROUND_MUSIC("Background Music:", "Musique de fond :"),
    MUSIC_VOLUME("Music Volume:", "Volume de la musique :"),
    ENABLE_SFX("Enable SFX", "Activer les effets sonores"),
    PLAY("Play", "Jouer"),
    CHANGE_CONTROLS("Change Controls:", "Changer les commandes :"),
    GUEST("Guest", "Invité"),
    SIGNUP_MENU("SIGNUP MENU", "MENU D'INSCRIPTION"),
    SECURITY_QUESTION("Security Question", "Question de sécurité"),
    SELECT_HERO("Select Hero", "Sélectionner un héros"),
    SELECT_WEAPON("Select Weapon", "Sélectionner une arme"),
    SELECT_TIME("Select Time", "Sélectionner la durée");

    private final String english;
    private final String french;

    Label(String english, String french) {
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
