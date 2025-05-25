package com.tildawn.Model.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.tildawn.Model.CollisionRect;

public class Tree extends Enemy {
    public Tree( float x,float y) {
        super(25000,x,y,false,0);
        Texture texture=new Texture(Gdx.files.internal("enemy/T_TreeMonsterWalking.png"));
        collisionRect=new CollisionRect(x,y,texture.getWidth(),texture.getHeight());
        sprite=new Sprite(texture);
        sprite.setPosition(x,y);
    }

    @Override
    public int spawnRate(float timeSurvived) {
        return 0;
    }

    @Override
    public void update(float deltaTime, Vector2 playerPos) {
       super.positionUpdate(deltaTime, playerPos);
    }
}
