package com.tildawn.Model.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.tildawn.Model.CollisionRect;

public class EyeBat extends Enemy {
    private float shootTimer = 0f;
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

    }
}
