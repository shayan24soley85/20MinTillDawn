package com.tildawn.Model.enemy;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tildawn.Model.CollisionRect;

public class Tree extends enemy{
    public Tree( float x,float y, CollisionRect collisionRect) {
        super(25000, collisionRect,x,y,new Sprite(),false,0);
    }

    @Override
    public int spawnRate(float timeSurvived) {
        return 0;
    }
}
