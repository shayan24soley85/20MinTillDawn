package com.tildawn.Controller;

import com.tildawn.Main;
import com.tildawn.Model.Character;
import com.tildawn.Model.GameAssetManager;
import com.tildawn.Model.Weapon;
import com.tildawn.View.GameView;
import com.tildawn.View.MainMenuView;
import com.tildawn.View.PreGameMenuView;

public class PreGameMenuController {
    private PreGameMenuView view;


    public void setView(PreGameMenuView view) {
        this.view = view;
    }

    public PreGameMenuView getView() {
        return view;
    }
    public void handleButtonClick() {
        if (view != null) {
            if (view.getBackButton().isChecked()){
                view.getBackButton().setChecked(false);
                Main.getMain().setScreen(new MainMenuView
                    (new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            } else if (view.getPlayButton().isChecked()) {
                view.getPlayButton().setChecked(false);
                Character character=new Character(view.getHeroSelect().getSelected(),new Weapon(view.getWeaponSelect().getSelected()));
                Main.getMain().getApp().getCurrentGame().setGameTime(view.getTimeSelect().getSelected());
                Main.getMain().getApp().getCurrentGame().setCharacter(character);
                //todo  create map  & start game
                Main.getMain().setScreen(new GameView(new GameController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        }
    }

}
