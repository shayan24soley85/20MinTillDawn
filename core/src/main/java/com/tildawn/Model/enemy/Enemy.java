package com.tildawn.Model.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.tildawn.Main;
import com.tildawn.Model.CollisionRect;
import com.tildawn.Model.GameAssetManager;

public abstract class Enemy {
    protected int hp;
    protected CollisionRect collisionRect;
    protected boolean isAlive=true;
    protected boolean hit=false;
    protected final boolean canWalk;
    protected float posX;
    protected float posY;
    protected Sprite sprite;
    protected float time=0;
    protected EnemyState state=EnemyState.simple;
    protected float animationTimer = 0f;
    protected int speed;
    protected Texture texture1=new Texture(Gdx.files.internal("Sprite/DeathFX/DeathFX_0.png"));
    protected Texture texture2=new Texture(Gdx.files.internal("Sprite/DeathFX/DeathFX_1.png"));
    protected Texture texture3=new Texture(Gdx.files.internal("Sprite/DeathFX/DeathFX_2.png"));
    protected Texture texture4=new Texture(Gdx.files.internal("Sprite/DeathFX/DeathFX_3.png"));
    protected final Animation<Texture> deadAnimation=new Animation<>(0.1f, texture1, texture2, texture3, texture4);
    public abstract int spawnRate(float timeSurvived);
    public abstract void update(float deltaTime, Vector2 playerPos);
    public  void  positionUpdate(float deltaTime, Vector2 playerPos) {
        Vector2 dir = new Vector2(playerPos.x - posX, playerPos.y - posY).nor();
        posX += dir.x * speed * deltaTime;
        posY += dir.y * speed * deltaTime;
        sprite.setPosition(posX, posY);
    }

    public Enemy(int hp,  float x, float y, boolean canWalk, int speed) {
        this.hp = hp;
        this.posX = x;
        this.posY = y;
        this.canWalk = canWalk;
        this.speed=speed;
    }
    public void idleAnimation(){

       sprite.setRegion(deadAnimation.getKeyFrame(time));

        if (!deadAnimation.isAnimationFinished(time)) {
           time=time + Gdx.graphics.getDeltaTime();
        }
        else {
            Main.getMain().getApp().getCurrentGame().getCharacter().increaseKills();
            isAlive=false;
            time=0;
        }

//        deadAnimation.setPlayMode(Animation.PlayMode.LOOP);
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
    public void increaseHp(int hp) {
        this.hp += hp;
    }

    public EnemyState getState() {
        return state;
    }

    public void setState(EnemyState state) {
        this.state = state;
    }

    public float getAnimationTimer() {
        return animationTimer;
    }

    public void setAnimationTimer(float animationTimer) {
        this.animationTimer = animationTimer;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public Texture getTexture1() {
        return texture1;
    }

    public void setTexture1(Texture texture1) {
        this.texture1 = texture1;
    }

    public Texture getTexture2() {
        return texture2;
    }

    public void setTexture2(Texture texture2) {
        this.texture2 = texture2;
    }

    public Texture getTexture3() {
        return texture3;
    }

    public void setTexture3(Texture texture3) {
        this.texture3 = texture3;
    }

    public Texture getTexture4() {
        return texture4;
    }

    public void setTexture4(Texture texture4) {
        this.texture4 = texture4;
    }

    public Animation<Texture> getDeadAnimation() {
        return deadAnimation;
    }
}
