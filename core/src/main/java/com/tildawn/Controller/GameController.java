
package com.tildawn.Controller;

import com.tildawn.Controller.MapController;
import com.tildawn.Controller.PlayerController;
import com.tildawn.Controller.WeaponController;
import com.tildawn.Main;
import com.tildawn.Model.Character;
import com.tildawn.Model.Weapon;
import com.tildawn.View.GameView;

public class GameController {
    private GameView view;
    private PlayerController playerController;
    private MapController worldController;
    private WeaponController weaponController;


    public void setView(GameView view) {
        this.view = view;
        playerController = new PlayerController(Main.getMain().getApp().getCurrentGame().getCharacter());
        worldController = new MapController(playerController);
        weaponController = new WeaponController(Main.getMain().getApp().getCurrentGame().getCharacter().getWeapon());
    }

    public void updateGame() {
        if (view != null) {
            worldController.update();
            playerController.update();
            weaponController.update();
        }
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public WeaponController getWeaponController() {
        return weaponController;
    }
}
