package com.tildawn.Model.enemy;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tildawn.Model.CollisionRect;

public abstract class Enemy {
    protected int hp;
    protected CollisionRect collisionRect;
    protected boolean isAlive=true;
    protected boolean hit=false;
    protected final boolean canWalk;
    protected float posX;
    protected float posY;
    protected Sprite sprite;
    protected int speed;
    public abstract int spawnRate(float timeSurvived);


    public Enemy(int hp,  float x, float y, boolean canWalk, int speed) {
        this.hp = hp;
        this.posX = x;
        this.posY = y;
        this.canWalk = canWalk;
        this.speed=speed;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public CollisionRect getCollisionRect() {
        return collisionRect;
    }

    public void setCollisionRect(CollisionRect collisionRect) {
        this.collisionRect = collisionRect;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public boolean isCanWalk() {
        return canWalk;
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

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
