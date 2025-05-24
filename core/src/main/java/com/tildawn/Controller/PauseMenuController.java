package com.tildawn.Controller;

import com.tildawn.Main;
import com.tildawn.Model.GameAssetManager;
import com.tildawn.View.PauseMenuView;
import com.tildawn.View.SettingMenuView;

public class PauseMenuController {
    private PauseMenuView view;
    public void setView(PauseMenuView view) {
        this.view = view;
    }

    public PauseMenuView getView() {
        return view;
    }
    public void handleButtonPressed() {
       if(view!=null){
           if (view.getResumeButton().isChecked()){
               view.getResumeButton().setChecked(false);
               Main.getMain().setScreen(Main.getMain().getApp().getCurrentGame().getGameView());
           } else if (view.getSettingsButton().isChecked()) {
               view.getSettingsButton().setChecked(false);
               Main.getMain().setScreen(new SettingMenuView(new SettingMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
           }
       }
    }
}
