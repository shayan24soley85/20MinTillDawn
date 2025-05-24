package com.tildawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tildawn.Enums.WeaponType;

public class Weapon {
    private Sprite sprite;
    private int ammo ;
    private WeaponType type;
    private boolean isReloading = false;

    public Weapon(WeaponType type){
        this.type = type;
        sprite=new Sprite(type.getTexture());
        sprite.setX((float) Gdx.graphics.getWidth() / 2 );
        sprite.setY((float) Gdx.graphics.getHeight() / 2);
        sprite.setSize(50,50);
        ammo=type.getMaxAmmo();

    }

    public Sprite getSprite() {
        return sprite;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo){
        this.ammo = ammo;
    }


    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public WeaponType getType() {
        return type;
    }

    public void setType(WeaponType type) {
        this.type = type;
    }

    public boolean isReloading() {
        return isReloading;
    }

    public void setReloading(boolean reloading) {
        isReloading = reloading;
    }
}
