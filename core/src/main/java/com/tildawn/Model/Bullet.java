package com.tildawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tildawn.Main;

public class Bullet {
    private Texture texture = new Texture(GameAssetManager.getGameAssetManager().getBullet());
    private Sprite sprite = new Sprite(texture);
    private int damage = 5;
    private int x;
    private int y;

    public Bullet(int x, int y){
        sprite.setSize(20 , 20);
        this.x = x;
        this.y = y;
        sprite.setX(Main.getMain().getApp().getCurrentGame().getCharacter().getPosX());
        sprite.setY(Main.getMain().getApp().getCurrentGame().getCharacter().getPosY());
    }

    public Texture getTexture() {
        return texture;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public int getDamage() {
        return damage;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
