package com.tildawn.Controller;

import com.tildawn.Main;
import com.tildawn.Model.Game;
import com.tildawn.Model.GameAssetManager;
import com.tildawn.Model.Language.Language;
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
                Game game=Main.getMain().getApp().getCurrentGame();
                game.setAutoReload(view.getAutoReload().isChecked());
                game.setSfxToggle(view.getSfxToggle().isChecked());
                game.setGrayscaleToggle(view.getGrayscaleToggle().isChecked());
                Main.getMain().getApp().getCurrentLanguage().setCurrentLanguage(view.getLanguage().getSelected().equals("ENGLISH")? Language.English:Language.French);
                //todo music changes !!
            } else if (view.getSetButtons().isChecked()) {
                view.getSetButtons().setChecked(false);
                //todo go to change button menu
            }
        }
    }
}
