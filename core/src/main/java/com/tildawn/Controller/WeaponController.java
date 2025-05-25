package com.tildawn.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.tildawn.Enums.Message;
import com.tildawn.Enums.SFX;
import com.tildawn.Main;
import com.tildawn.Model.Bullet;
import com.tildawn.Model.Weapon;

import java.util.ArrayList;

public class WeaponController {
    private long reloadStartTime = 0;
    private boolean isReloadingScheduled = false;

    private Weapon weapon;
    private ArrayList<Bullet> bullets = new ArrayList<>();

    public WeaponController(Weapon weapon){
        this.weapon = weapon;
    }
    public void handlePlayerInput(){
        if (Gdx.input.isKeyPressed(Input.Keys.R) && !weapon.isReloading() && !isReloadingScheduled) {
            Main.getMain().getApp().getCurrentGame().getGameView().setErrorMessage(Message.RELOADING.getText());
            SFX.RELOAD.play();
            weapon.setReloading(true);
            isReloadingScheduled = true;
            reloadStartTime = System.currentTimeMillis();
        }
    }
    public void update(){
        if (isReloadingScheduled) {
            if (System.currentTimeMillis() - reloadStartTime >= 2000) {
                weapon.setAmmo(weapon.getType().getMaxAmmo());
                weapon.setReloading(false);
                isReloadingScheduled = false;
            }
        }
        weapon.getSprite().draw(Main.getBatch());
        handlePlayerInput();
        updateBullets();
    }

    public void handleWeaponRotation(int x, int y) {
        Sprite weaponSprite = weapon.getSprite();

        float weaponCenterX = (float) Gdx.graphics.getWidth() / 2;
        float weaponCenterY = (float) Gdx.graphics.getHeight() / 2;

        float angle = (float) Math.atan2(y - weaponCenterY, x - weaponCenterX);

        weaponSprite.setRotation((float) (3.14 - angle * MathUtils.radiansToDegrees));
    }

    public void handleWeaponShoot(int x, int y){
        if (weapon.isReloading() || isReloadingScheduled) {
            SFX.NEED_AMMO.play();
            Main.getMain().getApp().getCurrentGame().getGameView().setErrorMessage(Message.CANT_SHOOT_WHILE_RELOADING.getText());
            return;
        }

        if (weapon.getAmmo() <= 0) {
            if (Main.getMain().getApp().getCurrentGame().getAutoReload()) {
                weapon.setReloading(true);
                isReloadingScheduled = true;
                reloadStartTime = System.currentTimeMillis();
                Main.getMain().getApp().getCurrentGame().getGameView().setErrorMessage(Message.RELOADING.getText());
                SFX.RELOAD.play();
            } else {
                SFX.NEED_AMMO.play();
                Main.getMain().getApp().getCurrentGame().getGameView().setErrorMessage(Message.NO_AMMO.getText());
            }
            return;
        }
        for (int i = 0; i < weapon.getType().getProjectTile(); i++) {
            float delay = i * 0.1f; // فاصله بین شلیک‌ها مثلاً ۰.۱ ثانیه
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    bullets.add(new Bullet(x, y));
                    SFX.SHOOT.play();
                }
            }, delay);
        }
        weapon.setAmmo(weapon.getAmmo() - 1);
    }


    public void updateBullets() {
        for(Bullet b : bullets) {
            b.getSprite().draw(Main.getBatch());
            Vector2 direction = new Vector2(
                Gdx.graphics.getWidth()/2f - b.getX(),
                Gdx.graphics.getHeight()/2f - b.getY()
            ).nor();

            b.getSprite().setX(b.getSprite().getX() - direction.x * 5);
            b.getSprite().setY(b.getSprite().getY() + direction.y * 5);
        }
    }
}
