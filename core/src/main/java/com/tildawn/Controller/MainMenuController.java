package com.tildawn.Controller;

import com.tildawn.Main;
import com.tildawn.Model.GameAssetManager;
import com.tildawn.View.LoginMenuView;
import com.tildawn.View.MainMenuView;
import com.tildawn.View.SettingMenuView;

public class MainMenuController {
    private MainMenuView view;

    public void setView(MainMenuView view) {
        this.view = view;
    }

    public void handleMainMenuButtons() {
        if (view != null) {
           if(view.getLogoutButton().isChecked()){
               Main.getMain().getApp().setCurrentUser(null);
               view.getLogoutButton().setChecked(false);
               Main.getMain().setScreen(new LoginMenuView
                   (new LoginMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
           } else if (view.getSettingsButton().isChecked()) {
               view.getLogoutButton().setChecked(false);
               Main.getMain().setScreen(new SettingMenuView
                   (new SettingMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
           }
        }
    }
}
