package com.tildawn.Controller;

import com.tildawn.Main;
import com.tildawn.Model.GameAssetManager;
import com.tildawn.View.MainMenuView;
import com.tildawn.View.SettingMenuView;

public class SettingMenuController {
    private SettingMenuView view;

    public void setView(SettingMenuView view) {
        this.view = view;
    }

    public SettingMenuView getView() {
        return view;
    }
    public void handleButtonClick() {
        if(view!=null){
            if(view.getBackButton().isChecked()){
                view.getBackButton().setChecked(false);
                Main.getMain().setScreen(new MainMenuView
                    (new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            } else if (view.getConfirmButton().isChecked()) {
                view.getConfirmButton().setChecked(false);
                //todo confirm setting changes
            } else if (view.getSetButtons().isChecked()) {
                //todo go to change button menu
            }
        }
    }
}
