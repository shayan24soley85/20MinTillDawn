
package com.tildawn.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Timer;
import com.tildawn.Controller.MapController;
import com.tildawn.Controller.PlayerController;
import com.tildawn.Controller.WeaponController;
import com.tildawn.Enums.AbilityType;
import com.tildawn.Enums.Message;
import com.tildawn.Enums.SFX;
import com.tildawn.Main;
import com.tildawn.Model.Bullet;
import com.tildawn.Model.Character;
import com.tildawn.Model.GameAssetManager;
import com.tildawn.Model.Weapon;
import com.tildawn.Model.enemy.*;
import com.tildawn.View.*;

import java.util.ArrayList;

public class GameController {
    private GameView view;
    private PlayerController playerController;
    private MapController worldController;
    private WeaponController weaponController;
    private enemyController enemyControl;
    private ArrayList<xpDrops> drops=new ArrayList<>();


    public void setView(GameView view) {
        this.view = view;
        playerController = new PlayerController(Main.getMain().getApp().getCurrentGame().getCharacter());
        worldController = new MapController(playerController);
        weaponController = new WeaponController(Main.getMain().getApp().getCurrentGame().getCharacter().getWeapon());
        enemyControl=new enemyController();
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
                    Main.getMain().setScreen(new ChooseAbilityView(new ChooseAbilityController(), GameAssetManager.getGameAssetManager().getSkin()));
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
                enemyControl.setBossStarted(true);
            }
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.P)){
            Main.getMain().getApp().getCurrentGame().setInPause(true);
            Main.getMain().setScreen(new PauseMenuView(new PauseMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
        }
    }
    public void enemyKnockBack(Enemy enemy, Character player) {
        float knockBackDistance = 10f;

        float ex = enemy.getPosX();
        float ey = enemy.getPosY();
        float px = player.getPosX();
        float py = player.getPosY();

        if (ex > px && ey == py) {
            enemy.setPosX(ex + knockBackDistance);
        }
        else if (ex < px && ey == py) {

            enemy.setPosX(ex - knockBackDistance);
        }
        else if (ey > py && ex == px) {

            enemy.setPosY( ey + knockBackDistance);
        }
        else if (ey < py && ex == px) {

            enemy.setPosY( ey - knockBackDistance);
        }
        else if (ex > px && ey > py) {

            enemy.setPosY(ey + knockBackDistance);
            enemy.setPosX(ex + knockBackDistance);
        }
        else if (ex > px && ey < py) {
            enemy.setPosY(ey - knockBackDistance);
            enemy.setPosX(ex + knockBackDistance);
        }
        else if (ex < px && ey > py) {
            enemy.setPosY(ey + knockBackDistance);
            enemy.setPosX(ex - knockBackDistance);
        }
        else if (ex < px && ey < py) {
            enemy.setPosY(ey - knockBackDistance);
            enemy.setPosX(ex - knockBackDistance);
        }
        else {
            enemy.setPosY(ey + knockBackDistance);
            enemy.setPosX(ex + knockBackDistance);
        }
    }

    public void checkCollisionRects(float delta){
        for(Enemy enemy:enemyControl.getAllMapEnemies()){
            for (Bullet bullet: weaponController.getBullets()){
                if (enemy.getCollisionRect().collidesWith(bullet.getCollisionRect())){
                    enemy.increaseHp(-weaponController.getWeapon().getType().getDamage());
                    if (!(enemy instanceof Tree)){
                        enemyKnockBack(enemy,playerController.getPlayer());
                    }

                    bullet.setDead(true);
                    if (enemy.getHp()<=0){
                        SFX.monsterDying.play();
                        enemy.setState(EnemyState.dying);
                        if (enemy instanceof Elder){
                            enemyControl.getWall().setActive(false);
                        }
                    }else {
                        enemy.setState(EnemyState.damaged);
                    }
                }
            }
            if (playerController.getPlayer().getRect().collidesWith(enemy.getCollisionRect())&&!playerController.getPlayer().isDamaged()){
                if (playerController.getPlayer().getAbilities().containsKey(AbilityType.VITALITY)){
                    playerController.getPlayer().setHp(playerController.getPlayer().getHp()-2);
                }else{
                    playerController.getPlayer().setHp(playerController.getPlayer().getHp()-1);
                }
                SFX.lowHealth.play();
                playerController.getPlayer().setInvincible(true);
                playerController.getPlayer().setDamaged(true);
                if(playerController.getPlayer().isDamaged()){
                    showAnimation(playerController.getPlayer(),delta);
                    Timer.schedule(new Timer.Task() {
                        @Override
                        public void run() {
                            playerController.getPlayer().getPlayerSprite().setRegion(playerController.getPlayer().getPlayerTexture());
                        }
                    }, 2);
                }

                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                       playerController.getPlayer().setInvincible(false);
                       playerController.getPlayer().setDamaged(false);
                    }
                }, 2);

            }
        }
        for (xpDrops drop: drops){
            if(drop.getCollisionRect().collidesWith(playerController.getPlayer().getRect())){
                drop.setCollected(true);
                SFX.collectCoin.play();
                boolean levelIncreased=playerController.getPlayer().increaseXp
                    (3);
                if (levelIncreased){
                    Main.getMain().setScreen(new ChooseAbilityView(new ChooseAbilityController(), GameAssetManager.getGameAssetManager().getSkin()));
                }
            }
        }
    }
    public void showAnimation(Character player,float delta){
        player.getPlayerSprite().setRegion(player.getAnimation().getKeyFrame(player.getTime()));

        player.setTime(player.getTime()+delta);
        if (player.getAnimation().isAnimationFinished(player.getTime()) || player.getTime() >= 1f) {

            Main.getMain().getApp().getCurrentGame().getCharacter().increaseKills();

          player.setTime(0);


            player.getPlayerSprite().setRegion(player.getPlayerTexture());
        }
    }
    public void updateCollisionRects(){
        for(Enemy enemy:enemyControl.getAllMapEnemies()){
            enemy.getCollisionRect().updateCollisionRect(enemy.getPosX(),enemy.getPosY());
        }
        playerController.getPlayer().getRect().updateCollisionRect(playerController.getPlayer().getPosX(),playerController.getPlayer().getPosY());
        for (Bullet bullet: weaponController.getBullets()){
            bullet.getCollisionRect().updateCollisionRect(bullet.getSprite().getX(), bullet.getSprite().getY());
        }
    }
    public void updateGame(float delta) {
        if (view != null) {
            drops.removeIf(xpDrops::isCollected);
            handleInput();
            playerController.update();
            weaponController.update();
            enemyControl.update(delta);
            updateCollisionRects();
            checkCollisionRects(delta);
        }
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public WeaponController getWeaponController() {
        return weaponController;
    }

    public GameView getView() {
        return view;
    }

    public void setPlayerController(PlayerController playerController) {
        this.playerController = playerController;
    }

    public MapController getWorldController() {
        return worldController;
    }

    public void setWorldController(MapController worldController) {
        this.worldController = worldController;
    }

    public void setWeaponController(WeaponController weaponController) {
        this.weaponController = weaponController;
    }

    public enemyController getEnemyControl() {
        return enemyControl;
    }

    public void setEnemyControl(enemyController enemyControl) {
        this.enemyControl = enemyControl;
    }

    public ArrayList<xpDrops> getDrops() {
        return drops;
    }

    public void setDrops(ArrayList<xpDrops> drops) {
        this.drops = drops;
    }
}
