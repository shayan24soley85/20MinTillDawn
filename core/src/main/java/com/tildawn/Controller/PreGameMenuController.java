package com.tildawn.Controller;

import com.tildawn.Enums.CharacterType;
import com.tildawn.Enums.SFX;
import com.tildawn.Enums.WeaponType;
import com.tildawn.Main;
import com.tildawn.Model.Character;
import com.tildawn.Model.Game;
import com.tildawn.Model.GameAssetManager;
import com.tildawn.Model.Weapon;
import com.tildawn.View.GameView;
import com.tildawn.View.MainMenuView;
import com.tildawn.View.PreGameMenuView;

import java.util.HashMap;

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
                SFX.CLICK_BUTTON.play();
                view.getBackButton().setChecked(false);
                Main.getMain().setScreen(new MainMenuView
                    (new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            } else if (view.getPlayButton().isChecked()) {
                SFX.CLICK_BUTTON.play();
                view.getPlayButton().setChecked(false);
                Character character=new Character(view.getHeroSelect().getSelected(),new Weapon(view.getWeaponSelect().getSelected()));
                character.setAbilities(new HashMap<>());
                character.setUsername(Main.getMain().getApp().getCurrentUser().getUsername());
                Main.getMain().getApp().getNewGame().setGameTime(view.getTimeSelect().getSelected());
                Main.getMain().getApp().getNewGame().setCharacter(character);
                Main.getMain().getApp().getNewGame().setId(Main.getMain().getApp().getAllGames().size());
                Main.getMain().getApp().setCurrentGame(Main.getMain().getApp().getNewGame());
                Main.getMain().getApp().getAllGames().put(Main.getMain().getApp().getNewGame().getId(),Main.getMain().getApp().getNewGame());
                Main.getMain().setScreen(new GameView(new GameController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        }
    }

}
