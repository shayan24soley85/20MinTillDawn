package com.tildawn.Controller;

import com.badlogic.gdx.utils.Timer;
import com.tildawn.Enums.Message;
import com.tildawn.Main;
import com.tildawn.Model.GameAssetManager;
import com.tildawn.Model.User;
import com.tildawn.View.*;

public class ProfileMenuController {
    private ProfileMenuView view;
    private User user=Main.getMain().getApp().getCurrentUser();

    public void setView(ProfileMenuView view) {
        this.view = view;
    }

    public ProfileMenuView getView() {
        return view;
    }
    public void handleClickButton() {
        if(view!=null) {
            String username = view.getUsername().getText();
            String password = view.getPassword().getText();
            if(view.getBackButton().isChecked()){
                view.getBackButton().setChecked(false);
                Main.getMain().setScreen(new MainMenuView
                    (new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            } else if (view.getChangePasswordButton().isChecked()) {
                view.getChangePasswordButton().setChecked(false);
                if (password.isEmpty()) {
                    return;
                }
                if (!SignupMenuController.isStrongPassword(password)) {
                    view.setErrorMessage(Message.WEAK_PASSWORD.toString());
                    return;
                }
                view.setSuccessMessage(Message.PASSWORD_CHANGED.toString());
               user.setPassword(password);
                Main.getMain().getApp().getSaving().saveUserToJson(user);
            } else if (view.getChangeUsernameButton().isChecked()) {
                view.getChangeUsernameButton().setChecked(false);
                if(username.isEmpty()) {
                    return;
                }
                if (!SignupMenuController.userIsValid(username)) {
                    view.setErrorMessage(Message.USERNAME_ALREADY_IN_USE.toString());
                    return;
                }
                view.setSuccessMessage(Message.USERNAME_CHANGED.toString());
                Main.getMain().getApp().getSaving().removeUserFromJSON(user.getUsername());
                Main.getMain().getApp().getAllUsers().remove(user.getUsername());
                User newUser = Main.getMain().getApp().getCurrentUser();
                user.setUsername(username);
                Main.getMain().getApp().getSaving().saveUserToJson(newUser);
            } else if (view.getDeleteAccountButton().isChecked()) {
                view.setSuccessMessage(Message.ACCOUNT_DELETED.toString());

                Main.getMain().getApp().getSaving().removeUserFromJSON(user.getUsername());
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        Main.getMain().setScreen(new LoginMenuView(new LoginMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
                    }
                }, 5);
            } else if (view.getChangeProfileButton().isChecked()) {
                view.getChangeProfileButton().setChecked(false);
                Main.getMain().setScreen(new ChangeAvatarMenuView(new ChangeAvatarMenuController(), GameAssetManager.getGameAssetManager().getSkin()));

            }
        }
    }

}
