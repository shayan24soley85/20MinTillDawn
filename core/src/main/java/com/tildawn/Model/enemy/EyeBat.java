package com.tildawn.Model.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.tildawn.Main;
import com.tildawn.Model.CollisionRect;

import java.util.ArrayList;

public class EyeBat extends Enemy {
    private float shootTimer = 0f;
    private ArrayList<EyeBatBullet> batBullets=new ArrayList<>();
    public EyeBat( float x,float y) {
        super(50,x,y,true,30);
        Texture texture=new Texture(Gdx.files.internal("enemy/T_EyeBat_0.png"));
        collisionRect=new CollisionRect(x,y,texture.getWidth(),texture.getHeight());
        sprite=new Sprite(texture);
    }

    @Override
    public int spawnRate(float timeSurvived) {
        return 0;
    }

    @Override
    public void update(float deltaTime, Vector2 playerPos) {
        super.positionUpdate(deltaTime, playerPos);

        shootTimer += deltaTime;
        if (shootTimer >= 3f) {
            shootTimer = 0f;
            shootAtPlayer(playerPos);
        }
        animationTimer += deltaTime;
        for (EyeBatBullet bullet : batBullets) {
            bullet.getSprite().setPosition(bullet.getX(), bullet.getY());
            bullet.getSprite().draw(Main.getBatch());
            Vector2 direction = new Vector2(
                Gdx.graphics.getWidth() / 2f - bullet.getX(),
                Gdx.graphics.getHeight() / 2f - bullet.getY()
            ).nor();
            bullet.getSprite().setX(bullet.getSprite().getX() - direction.x * 5);
            bullet.getSprite().setY(bullet.getSprite().getY() + direction.y * 5);
        }
        switch (state) {
            case damaged:
                if (animationTimer >= 0.3f) {
                    state = EnemyState.simple;
                }
                break;

            case dying:
                if (animationTimer >= 1.0f) {
                    idleAnimation();
                }
                break;

            case simple:
            default:

                break;
        }

    }
    private void shootAtPlayer(Vector2 playerPos) {
         //batBullets.add(new EyeBatBullet((int)playerPos.x,(int)playerPos.y,(int)posX,(int)posY));
    }

    public ArrayList<EyeBatBullet> getBatBullets() {
        return batBullets;
    }

    public void setBatBullets(ArrayList<EyeBatBullet> batBullets) {
        this.batBullets = batBullets;
    }
}
