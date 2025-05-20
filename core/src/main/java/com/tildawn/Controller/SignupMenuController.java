package com.tildawn.Controller;

import com.tildawn.Main;
import com.tildawn.Model.GameAssetManager;
import com.tildawn.Model.User;
import com.tildawn.View.PreGameMenuView;
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
    public void signup(String username, String password,String securityQuestion, String securityAnswer) {
        User user=new User(username,password,securityQuestion,securityAnswer);
        Main.getMain().getApp().getAllUsers().put(user.getUsername(), user);
        Main.getMain().getApp().getSaving().saveUserToJson(user);
    }
    public void handleSignupMenuButtonClicked() {
        if (view != null) {
            String password=view.getPassword().getText();
            String username=view.getUsername().getText();
            String confirmPassword=view.getConfirmPassword().getText();
            String securityQuestion=view.getSecurityQuestion().getText();
            String answer=view.getSecurityAnswer().getText();
            if (view.getSignupButton().isChecked()) {
                if(password.isEmpty() ||username.isEmpty()||confirmPassword.isEmpty()
                    ||securityQuestion.isEmpty()||answer.isEmpty()) {
                    view.getSignupButton().setChecked(false);
                    view.setErrorMessage("Please fill all the fields");
                    return;
                }
                else if (!password.equals(confirmPassword)) {
                    view.setErrorMessage("Passwords do not match!");
                    view.getSignupButton().setChecked(false);
                    return;
                } else if (!isStrongPassword(password)) {
                    view.setErrorMessage("you must enter a strong password!");
                    view.getSignupButton().setChecked(false);
                    return;
                } if (!userIsValid(username)) {
                    view.setErrorMessage("this username is already in use!");
                    view.getSignupButton().setChecked(false);
                    return;

                }else {
                    signup(username,password,securityQuestion,answer);
                    Main.getMain().getScreen().dispose();
                    //Main.getMain().setScreen(new PreGameMenuView(new PreGameMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
                }
                return;
            }
        }
    }
}
