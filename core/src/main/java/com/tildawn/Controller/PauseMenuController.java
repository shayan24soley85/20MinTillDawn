package com.tildawn.Controller;

import com.tildawn.Enums.SFX;
import com.tildawn.Main;
import com.tildawn.Model.GameAssetManager;
import com.tildawn.View.PauseMenuView;
import com.tildawn.View.SettingMenuView;
import com.tildawn.View.TalentMenuView;

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
               SFX.CLICK_BUTTON.play();
               view.getResumeButton().setChecked(false);
               Main.getMain().getApp().getCurrentGame().setInPause(false);
               Main.getMain().setScreen(Main.getMain().getApp().getCurrentGame().getGameView());
           } else if (view.getSettingsButton().isChecked()) {
               SFX.CLICK_BUTTON.play();
               view.getSettingsButton().setChecked(false);
               Main.getMain().setScreen(new SettingMenuView(new SettingMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
           } else if (view.getTalentButton().isChecked()) {
               SFX.CLICK_BUTTON.play();
               view.getTalentButton().setChecked(false);
               Main.getMain().setScreen(new TalentMenuView
                   (new TalentMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
           }
           //todo save and sxit , resign ...
       }
    }
}
