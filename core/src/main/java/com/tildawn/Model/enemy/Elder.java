package com.tildawn.Model.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tildawn.Model.CollisionRect;

public class Elder extends enemy {
    public Elder(float x,float y, CollisionRect collisionRect) {
        super(400, collisionRect,x,y,new Sprite(new Texture(Gdx.files.internal("enemy/ElderBrain.png"))),true,30);
    }

    @Override
    public int spawnRate(float timeSurvived) {
        return 0;
    }
}
