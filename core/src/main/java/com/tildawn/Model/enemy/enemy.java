package com.tildawn.Model.enemy;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tildawn.Model.CollisionRect;

public abstract class enemy {
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


    public enemy(int hp, CollisionRect collisionRect,float x,float y,Sprite sprite,boolean canWalk,int speed) {
        this.hp = hp;
        this.collisionRect = collisionRect;
        this.sprite = sprite;
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
}
