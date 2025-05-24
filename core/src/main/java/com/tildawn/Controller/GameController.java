
package com.tildawn.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.tildawn.Controller.MapController;
import com.tildawn.Controller.PlayerController;
import com.tildawn.Controller.WeaponController;
import com.tildawn.Enums.Message;
import com.tildawn.Main;
import com.tildawn.Model.Character;
import com.tildawn.Model.GameAssetManager;
import com.tildawn.Model.Weapon;
import com.tildawn.View.ChangeAvatarMenuView;
import com.tildawn.View.GameView;
import com.tildawn.View.PauseMenuView;

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
    public void handleInput(){
        boolean ctrlPressed = Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT) || Gdx.input.isKeyPressed(Input.Keys.CONTROL_RIGHT);

        if (ctrlPressed) {
            Character character = Main.getMain().getApp().getCurrentGame().getCharacter();
            if (Gdx.input.isKeyJustPressed(Input.Keys.Z)) {
                 Main.getMain().getApp().getCurrentGame().getGameView().increaseStartTimeMillis();
            }
            else if (Gdx.input.isKeyJustPressed(Input.Keys.X)) {
                boolean levelIncreased=character.increaseXp
                    (character.xpToNextLevel(character.getLevel())+character.maxLevelXp()-character.getXp());
                if (levelIncreased){
//todo ability menu
                }
            }
            else if (Gdx.input.isKeyJustPressed(Input.Keys.C)) {
                // cheat code  delkhaah khodm
                playerController.getPlayer().getWeapon().setAmmo(10000);
            }
            else if (Gdx.input.isKeyJustPressed(Input.Keys.V)) {
                 if(playerController.getPlayer().getHp()>=playerController.getPlayer().getType().getMaxHp()){
                     Main.getMain().getApp().getCurrentGame().getGameView().setErrorMessage(Message.YOUR_HP_IS_MAX.getText());
                     return;
                 }
                 playerController.getPlayer().setHp(playerController.getPlayer().getType().getMaxHp());
            }
            else if (Gdx.input.isKeyJustPressed(Input.Keys.B)) {

            }
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.P)){
            Main.getMain().getApp().getCurrentGame().setInPause(true);
            Main.getMain().setScreen(new PauseMenuView(new PauseMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
        }
    }
    public void updateGame() {
        if (view != null) {
            handleInput();
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
