package com.tildawn.Model.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.tildawn.Model.CollisionRect;

public class Elder extends Enemy {
    public Elder(float x,float y) {
        super(400,x,y,true,30);
        Texture texture=new Texture(Gdx.files.internal("enemy/ElderBrain.png"));
        collisionRect=new CollisionRect(x,y,texture.getWidth()*3,texture.getHeight()*3);
        sprite=new Sprite(texture);
        sprite.setSize(texture.getWidth()*3,texture.getHeight()*3);
    }

    @Override
    public int spawnRate(float timeSurvived) {
        return 0;
    }

    @Override
    public void update(float deltaTime, Vector2 playerPos) {
         super.positionUpdate(deltaTime, playerPos);
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
}
