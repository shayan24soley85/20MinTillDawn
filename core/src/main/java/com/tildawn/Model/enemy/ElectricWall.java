package com.tildawn.Model.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.tildawn.Model.Character;

public class ElectricWall {
    private Rectangle bounds;
    private float shrinkSpeed;
    private boolean active;
    float rotation = 0f;
    private Animation<Texture> animation=new Animation<>(0.1f,new Texture[]{
        new Texture("Texture2D/T/T_ElectricWall1.png"),
        new Texture("Texture2D/T/T_ElectricWall2.png")
        ,new Texture("Texture2D/T/T_ElectricWall3.png")
        ,new Texture("Texture2D/T/T_ElectricWall4.png")
        ,new Texture("Texture2D/T/T_ElectricWall5.png")
        ,new Texture("Texture2D/T/T_ElectricWall6.png")
    });
    private float wallThickness = 10f;
    public ElectricWall(float shrinkSpeed) {
        bounds = new Rectangle(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.shrinkSpeed = shrinkSpeed;
        active = false;
    }

    public void update(float delta) {
        if (!active) return;
        rotation += delta;
        float shrinkAmount = shrinkSpeed * delta;
        bounds.x += shrinkAmount / 2;
        bounds.y += shrinkAmount / 2;
        bounds.width -= shrinkAmount;
        bounds.height -= shrinkAmount;

        if (bounds.width <= 0 || bounds.height <= 0) {
            bounds.setSize(0, 0);
            active = false;
        }
    }

    public boolean isCharacterTouchingWall(Character character) {
        Rectangle charRect = character.getRect().toRectangle();
        Rectangle topWall = new Rectangle(bounds.x, bounds.y + bounds.height - wallThickness, bounds.width, wallThickness);
        Rectangle bottomWall = new Rectangle(bounds.x, bounds.y, bounds.width, wallThickness);
        Rectangle leftWall = new Rectangle(bounds.x, bounds.y, wallThickness, bounds.height);
        Rectangle rightWall = new Rectangle(bounds.x + bounds.width - wallThickness, bounds.y, wallThickness, bounds.height);
        return topWall.overlaps(charRect) || bottomWall.overlaps(charRect) || leftWall.overlaps(charRect) || rightWall.overlaps(charRect);
    }

    public void draw(SpriteBatch batch) {
        if (!active) return;

        Texture currentFrame = animation.getKeyFrame(rotation, true);

        float rotation = 0f;
        float drawWidth = bounds.width;
        float drawHeight = bounds.height;


        if (isVerticalWall()) {
            rotation = 90f;

            drawWidth = bounds.height;
            drawHeight = bounds.width;
        }

        float originX = drawWidth / 2f;
        float originY = drawHeight / 2f;

        batch.begin();

        batch.end();
    }


    private boolean isVerticalWall() {

        return bounds.height > bounds.width;
    }

    public boolean isActive() {
        return active;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public float getShrinkSpeed() {
        return shrinkSpeed;
    }

    public void setShrinkSpeed(float shrinkSpeed) {
        this.shrinkSpeed = shrinkSpeed;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public float getWallThickness() {
        return wallThickness;
    }

    public void setWallThickness(float wallThickness) {
        this.wallThickness = wallThickness;
    }

    public Animation<Texture> getAnimation() {
        return animation;
    }

    public void setAnimation(Animation<Texture> animation) {
        this.animation = animation;
    }
}
