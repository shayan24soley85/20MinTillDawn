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
    private boolean dead = false;
    private CollisionRect collisionRect;

    public Bullet(int x, int y){
        sprite.setSize(20 , 20);
        this.x = x;
        this.y = y;
        sprite.setX(Main.getMain().getApp().getCurrentGame().getCharacter().getPosX());
        sprite.setY(Main.getMain().getApp().getCurrentGame().getCharacter().getPosY());
        collisionRect=new CollisionRect(x,y,sprite.getWidth(),sprite.getHeight());
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

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public CollisionRect getCollisionRect() {
        return collisionRect;
    }

    public void setCollisionRect(CollisionRect collisionRect) {
        this.collisionRect = collisionRect;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }
}
