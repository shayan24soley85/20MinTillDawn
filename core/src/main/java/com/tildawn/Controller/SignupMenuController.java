package com.tildawn.Controller;

import com.tildawn.Enums.Avatar;
import com.tildawn.Enums.Message;
import com.tildawn.Enums.SFX;
import com.tildawn.Main;
import com.tildawn.Model.GameAssetManager;
import com.tildawn.Model.User;
import com.tildawn.View.LoginMenuView;
import com.tildawn.View.MainMenuView;
import com.tildawn.View.SignupMenuView;
public class SignupMenuController {
    private SignupMenuView view;

    public void setView(SignupMenuView view) {
        this.view = view;
    }
    public static boolean isStrongPassword(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }
        boolean hasSpecial = false;
        boolean hasDigit = false;
        boolean hasUpper = false;

        String specialChars = "@%$#&*()_";

        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                hasUpper = true;
            } else if (Character.isDigit(ch)) {
                hasDigit = true;
            } else if (specialChars.indexOf(ch) >= 0) {
                hasSpecial = true;
            }
        }

        return hasSpecial && hasDigit && hasUpper;
    }
    public static Boolean userIsValid(String username) {
        return !Main.getMain().getApp().getAllUsers().containsKey(username);
    }
    public void signup(String username, String password,String securityQuestion, String securityAnswer,Avatar avatar) {
        User user=new User(username,password,securityQuestion,securityAnswer, avatar.getPath());
        Main.getMain().getApp().getAllUsers().put(user.getUsername(), user);
        Main.getMain().getApp().setCurrentUser(user);
        Main.getMain().getApp().getSaving().saveUserToJson(user);

        Main.getMain().getApp().getDatabaseManager().insertUser(user);

    }
    public void handleSignupMenuButtonClicked() {
        if (view != null) {
            String password=view.getPassword().getText();
            String username=view.getUsername().getText();
            String confirmPassword=view.getConfirmPassword().getText();
            String securityQuestion=view.getSecurityQuestion().getSelected();
            String answer=view.getSecurityAnswer();
            if (view.getSignupButton().isChecked()) {
                SFX.CLICK_BUTTON.play();
                view.getSignupButton().setChecked(false);
                if(password.isEmpty() ||username.isEmpty()||confirmPassword.isEmpty()
                    ||answer.isEmpty()) {
                    view.setErrorMessage(Message.PLEASE_FILL_ALL_FIELDS.toString());
                    return;
                }
                else if (!password.equals(confirmPassword)) {
                    view.setErrorMessage(Message.PASSWORDS_DO_NOT_MATCH.toString());
                    return;
                } else if (!isStrongPassword(password)) {
                    view.setErrorMessage(Message.WRONG_PASSWORD.toString());
                    return;
                } if (!userIsValid(username)) {
                    view.setErrorMessage(Message.USERNAME_ALREADY_IN_USE.toString());
                    return;

                }else {
                    Avatar avatar=Avatar.getRandomAvatar();
                    signup(username,password,securityQuestion,answer,avatar);
                    Main.getMain().getScreen().dispose();
                    Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
                }
                return;
            } else if (view.getLoginButton().isChecked()) {
                SFX.CLICK_BUTTON.play();
                view.getLoginButton().setChecked(false);
                Main.getMain().setScreen(new LoginMenuView(new LoginMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            } else if (view.getPlayAsGuessButton().isChecked()) {
                SFX.CLICK_BUTTON.play();
                view.getPlayAsGuessButton().setChecked(false);
                Avatar avatar=Avatar.getRandomAvatar();
                User user=new User("GUEST",null,null,null, avatar.getPath());
                Main.getMain().getApp().setCurrentUser(user);
                Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        }
    }
}
