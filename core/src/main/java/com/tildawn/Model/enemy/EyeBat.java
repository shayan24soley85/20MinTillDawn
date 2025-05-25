package com.tildawn.Model.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tildawn.Model.CollisionRect;

public class EyeBat extends enemy{
    public EyeBat( float x,float y, CollisionRect collisionRect) {
        super(50, collisionRect,x,y,new Sprite(new Texture(Gdx.files.internal("enemy/T_EyeBat_0.png"))),true,30);
    }

    @Override
    public int spawnRate(float timeSurvived) {
        return 0;
    }
}
