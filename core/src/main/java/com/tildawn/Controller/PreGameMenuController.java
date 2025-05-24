package com.tildawn.Controller;

import com.tildawn.Enums.CharacterType;
import com.tildawn.Enums.SFX;
import com.tildawn.Enums.WeaponType;
import com.tildawn.Main;
import com.tildawn.Model.Character;
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
                Main.getMain().getApp().getCurrentGame().setGameTime(view.getTimeSelect().getSelected());
                //Character character=new Character(CharacterType.Diamond,new Weapon(WeaponType.dualSmg));
                Main.getMain().getApp().getCurrentGame().setCharacter(character);
               // todo  create map  & start game
                Main.getMain().setScreen(new GameView(new GameController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        }
    }

}
