package com.tildawn.Controller;

import com.badlogic.gdx.utils.Timer;
import com.tildawn.Enums.Message;
import com.tildawn.Enums.SFX;
import com.tildawn.Main;
import com.tildawn.Model.GameAssetManager;
import com.tildawn.View.*;

import java.util.ArrayList;

public class MainMenuController {
    private MainMenuView view;

    public void setView(MainMenuView view) {
        this.view = view;
    }

    public void handleMainMenuButtons() {
        if (view != null) {
           if(view.getLogoutButton().isChecked()){
               SFX.CLICK_BUTTON.play();
               Main.getMain().getApp().setCurrentUser(null);
               view.getLogoutButton().setChecked(false);
               view.setSuccessMessage(Message.LOGOUT_SUCCESS.toString());
               Timer.schedule(new Timer.Task() {
                   @Override
                   public void run() {
                       Main.getMain().setScreen(new LoginMenuView(new LoginMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
                   }
               }, 5);
           } else if (view.getSettingsButton().isChecked()) {
               SFX.CLICK_BUTTON.play();
               view.getSettingsButton().setChecked(false);
               Main.getMain().setScreen(new SettingMenuView
                   (new SettingMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
           } else if (view.getProfileButton().isChecked()) {
               SFX.CLICK_BUTTON.play();
               view.getProfileButton().setChecked(false);
               if (Main.getMain().getApp().getCurrentUser().getUsername().equals("GUEST")) {
                   view.setErrorMessage(Message.LOGIN_FIRST.toString());
                   return;
               }
               Main.getMain().setScreen(new ProfileMenuView
                   (new ProfileMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
           } else if (view.getPreGameButton().isChecked()) {
               SFX.CLICK_BUTTON.play();
               view.getPreGameButton().setChecked(false);
               Main.getMain().setScreen(new PreGameMenuView
                   (new PreGameMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
           } else if (view.getTalentButton().isChecked()) {
               SFX.CLICK_BUTTON.play();
               view.getTalentButton().setChecked(false);
               Main.getMain().setScreen(new TalentMenuView
                   (new TalentMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
           } else if (view.getScoreboardButton().isChecked()) {
               SFX.CLICK_BUTTON.play();
               view.getScoreboardButton().setChecked(false);
               Main.getMain().setScreen(new ScoreBoardMenuView( GameAssetManager.getGameAssetManager().getSkin(),new ArrayList<>(Main.getMain().getApp().getAllUsers().values()),new ScoreBoardMenuController()));
           }
        }
    }
}
