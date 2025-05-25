package com.tildawn.Model.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tildawn.Model.CollisionRect;

public class EyeBat extends Enemy {
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
}
