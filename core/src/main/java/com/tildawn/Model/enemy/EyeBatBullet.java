package com.tildawn.Model.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tildawn.Main;
import com.tildawn.Model.CollisionRect;
import com.tildawn.Model.GameAssetManager;

public class EyeBatBullet {
    private Texture texture = new Texture(GameAssetManager.getGameAssetManager().getBullet());
    private Sprite sprite = new Sprite(texture);
    private int damage = 1;
    private int x;
    private int y;
    private boolean dead = false;
    private CollisionRect collisionRect;

    public EyeBatBullet(int x, int y,int x2,int y2){
        sprite.setSize(20 , 20);
        this.x = x;
        this.y = y;
        sprite.setX(x2);
        sprite.setY(y2);
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

