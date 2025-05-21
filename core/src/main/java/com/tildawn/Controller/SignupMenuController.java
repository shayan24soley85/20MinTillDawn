package com.tildawn.Controller;

import com.tildawn.Enums.Avatar;
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
    public Boolean userIsValid(String username) {
        return !Main.getMain().getApp().getAllUsers().containsKey(username);
    }
    public void signup(String username, String password,String securityQuestion, String securityAnswer,Avatar avatar) {
        User user=new User(username,password,securityQuestion,securityAnswer, avatar.getPath());
        Main.getMain().getApp().getAllUsers().put(user.getUsername(), user);
        Main.getMain().getApp().setCurrentUser(user);
        Main.getMain().getApp().getSaving().saveUserToJson(user);
    }
    public void handleSignupMenuButtonClicked() {
        if (view != null) {
            String password=view.getPassword().getText();
            String username=view.getUsername().getText();
            String confirmPassword=view.getConfirmPassword().getText();
            String securityQuestion=view.getSecurityQuestion().getSelected();
            String answer=view.getSecurityAnswer();
            if (view.getSignupButton().isChecked()) {
                view.getSignupButton().setChecked(false);
                if(password.isEmpty() ||username.isEmpty()||confirmPassword.isEmpty()
                    ||answer.isEmpty()) {
                    view.setErrorMessage("Please fill all the fields");
                    return;
                }
                else if (!password.equals(confirmPassword)) {
                    view.setErrorMessage("Passwords do not match!");
                    return;
                } else if (!isStrongPassword(password)) {
                    view.setErrorMessage("you must enter a strong password!");
                    return;
                } if (!userIsValid(username)) {
                    view.setErrorMessage("this username is already in use!");
                    return;

                }else {
                    Avatar avatar=Avatar.getRandomAvatar();
                    signup(username,password,securityQuestion,answer,avatar);
                    Main.getMain().getScreen().dispose();
                    Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
                }
                return;
            } else if (view.getLoginButton().isChecked()) {
                view.getLoginButton().setChecked(false);
                Main.getMain().setScreen(new LoginMenuView(new LoginMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            } else if (view.getPlayAsGuessButton().isChecked()) {
                view.getPlayAsGuessButton().setChecked(false);
                Avatar avatar=Avatar.getRandomAvatar();
                User user=new User("GUEST",null,null,null, avatar.getPath());
                Main.getMain().getApp().setCurrentUser(user);
                Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        }
    }
}
