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
    SELECT_TIME("Select Time", "Sélectionner la durée"),
    AMMO("Ammo:", "Munitions:"),
    KILLS("Kills:", "Tues:"),
    HP("HP:", "PV:"),
    LEVEL("Level:", "Niveau:"),
    TIME("Time:", "Temps:"),RESUME_GAME("Resume the game", "Reprendre le jeu"),
    AVAILABLE_ABILITIES("Available abilities", "Capacités disponibles"),
    GIVE_UP("Give up", "Abandonner"),
    SAVE_AND_EXIT("Save and Exit", "Sauvegarder et quitter"),
    CHARACTERS("CHARACTERS:", "PERSONNAGES :"),
    CONTROLS("CONTROLS:", "COMMANDES :"),
    MOVEMENT("MOVEMENT:", "DÉPLACEMENT :"),
    KEYS_LINE1("W : UP       S : DOWN     A : LEFT     D : RIGHT", "W : HAUT       S : BAS     A : GAUCHE     D : DROITE"),
    KEYS_LINE2("R:RELOAD       MOUSE LEFT CLICK:SHOOT     P:PAUSE THE GAME", "R : RECHARGER     CLIC GAUCHE SOURIS : TIRER     P : METTRE EN PAUSE"),
    CHEATS("CHEATS:", "CODES DE TRICHE :"),
    CHEAT_Z("CTRL+Z :    Reduce remaining game time by 1 minute", "CTRL+Z :    Réduit le temps de jeu restant d'une minute"),
    CHEAT_X("CTRL+X :   Level up character with full animation and ability selection", "CTRL+X :   Monte de niveau avec animation et sélection de capacité"),
    CHEAT_C("CTRL+C :    Restore health when empty (only works with low health)", "CTRL+C :    Restaure la santé quand elle est vide (uniquement en cas de faible santé)"),
    CHEAT_V("CTRL+V :    Add 10000 Ammo to Player's Weapon", "CTRL+V :    Ajoute 10000 munitions à l’arme du joueur"),
    CHEAT_B("CTRL+B :    Start boss fight", "CTRL+B :    Déclenche le combat contre le boss"),
    ABILITIES("ABILITIES:", "CAPACITÉS :"),
    SIMPLE_KEYS("SIMPLE KEYS:", "TOUCHES SIMPLES :"),
    ABILITY_1("Increase maximum HP by 1 unit", "Augmente les PV max de 1 unité"),
    ABILITY_2("Increase weapon damage by 25% for 10 seconds", "Augmente les dégâts des armes de 25% pendant 10 secondes"),
    ABILITY_3("Double movement speed for 10 seconds", "Double la vitesse de déplacement pendant 10 secondes"),
    ABILITY_4("Increase weapon projectile count by 1", "Augmente le nombre de projectiles de l’arme de 1"),
    ABILITY_5("Increase maximum ammo by 5", "Augmente les munitions max de 5"),
    MAXHP("MAXHP", "PV MAX"),
    SPEED("SPEED", "VITESSE");

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
