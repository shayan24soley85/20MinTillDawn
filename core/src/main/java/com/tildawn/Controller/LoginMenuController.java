package com.tildawn.Controller;

import com.tildawn.Enums.Message;
import com.tildawn.Enums.SFX;
import com.tildawn.Main;
import com.tildawn.Model.GameAssetManager;
import com.tildawn.Model.User;
import com.tildawn.View.LoginMenuView;
import com.tildawn.View.MainMenuView;
import com.tildawn.View.RecoveryPasswordMenu;
import com.tildawn.View.SignupMenuView;

public class LoginMenuController {
    private LoginMenuView view;

    public void setView(LoginMenuView view) {
        this.view = view;
    }

    public LoginMenuView getView() {
        return view;
    }
    public static User findUserByUsername(String username){
        if(Main.getMain().getApp().getAllUsers().containsKey(username)) {
            return Main.getMain().getApp().getAllUsers().get(username);
        }
        return null;
    }
    public void handleButtonClicked() {
        if(view!=null){
            String username=view.getUsername().getText();
            String password=view.getPassword().getText();
            if(view.getLoginButton().isChecked()){
                SFX.CLICK_BUTTON.play();
                view.getLoginButton().setChecked(false);
                if (username.isEmpty() || password.isEmpty()) {
                    view.setErrorMessage(Message.PLEASE_FILL_ALL_FIELDS.toString());
                    return;
                } else if (!Main.getMain().getApp().getAllUsers().containsKey(username)) {
                    view.setErrorMessage(Message.USER_NOT_FOUND.toString());
                    return;
                }
                User user = findUserByUsername(username);
                assert user != null;
                if (!user.getPassword().equals(password)) {
                    view.setErrorMessage(Message.WRONG_PASSWORD.toString());
                    return;
                }
                Main.getMain().getApp().setCurrentUser(user);
                Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            } else if (view.getSignupButton().isChecked()) {
                SFX.CLICK_BUTTON.play();
                view.getSignupButton().setChecked(false);
                Main.getMain().setScreen(new SignupMenuView(new SignupMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            } else if (view.getRecoveryPasswordButton().isChecked()) {
                SFX.CLICK_BUTTON.play();
                view.getRecoveryPasswordButton().setChecked(false);
                if (!Main.getMain().getApp().getAllUsers().containsKey(username)) {
                    view.setErrorMessage(Message.USER_NOT_FOUND.toString());
                    return;
                }
                Main.getMain().setScreen(new RecoveryPasswordMenu(new RecoveryPasswordMenuController(), GameAssetManager.getGameAssetManager().getSkin(),findUserByUsername(username)));
            }
        }
    }
}
