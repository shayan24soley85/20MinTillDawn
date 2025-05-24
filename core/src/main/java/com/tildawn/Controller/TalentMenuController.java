package com.tildawn.Controller;

import com.tildawn.Enums.SFX;
import com.tildawn.Main;
import com.tildawn.Model.GameAssetManager;
import com.tildawn.View.MainMenuView;
import com.tildawn.View.PauseMenuView;
import com.tildawn.View.TalentMenuView;

public class TalentMenuController {
    private TalentMenuView view;
    public void setView(TalentMenuView view) {
        this.view = view;
    }

    public TalentMenuView getView() {
        return view;
    }
    public void handleButtonClick() {
        if (view!=null){
            if (view.getBackButton().isChecked()){
                SFX.CLICK_BUTTON.play();
                if(Main.getMain().getApp().getCurrentGame().isInPause()){
                    Main.getMain().setScreen(new PauseMenuView
                        (new PauseMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
                    return;
                }
                Main.getMain().setScreen(new MainMenuView
                    (new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        }
    }
}
