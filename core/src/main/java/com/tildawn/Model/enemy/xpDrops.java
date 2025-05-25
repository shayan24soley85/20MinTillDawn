package com.tildawn.Model.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tildawn.Model.CollisionRect;

public class xpDrops {
    private float posX, posY;
    private CollisionRect collisionRect;
    private boolean isCollected=false;
    private final Sprite sprite;

    public xpDrops(float posX, float posY) {
        this.posX = posX;
        this.posY = posY;
        sprite=new Sprite(new Texture(Gdx.files.internal("Texture2D/T/T_UISmallPanel.png")));
        sprite.setPosition(posX,posY);
        collisionRect=new CollisionRect(posX,posY,sprite.getWidth() ,sprite.getHeight());
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public CollisionRect getCollisionRect() {
        return collisionRect;
    }

    public void setCollisionRect(CollisionRect collisionRect) {
        this.collisionRect = collisionRect;
    }

    public boolean isCollected() {
        return isCollected;
    }

    public void setCollected(boolean collected) {
        isCollected = collected;
    }

    public Sprite getSprite() {
        return sprite;
    }
}
