package com.tildawn.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.tildawn.Main;
import com.tildawn.Model.Ability;
import com.tildawn.Model.Character;
import com.tildawn.Model.GameAssetManager;

public class PlayerController {
    private Character player;

    public PlayerController(Character player){
        this.player = player;
    }

    public void update(){
        for(Ability ability:player.getAbilities().values()){
            ability.update( Gdx.graphics.getDeltaTime());

        }
        player.getPlayerSprite().draw(Main.getBatch());

        if(player.isPlayerIdle()){
            idleAnimation();
        }

        handlePlayerInput();
    }


    public void handlePlayerInput(){
        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            player.setPosY(player.getPosY() - player.getType().getSpeed());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            player.setPosX(player.getPosX() - player.getType().getSpeed());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            player.setPosY(player.getPosY() + player.getType().getSpeed());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            player.setPosX(player.getPosX() + player.getType().getSpeed());
            player.getPlayerSprite().flip(true, false);
        }
    }


    public void idleAnimation(){
        Animation<Texture> animation = GameAssetManager.getGameAssetManager().getCharacter1_idle_animation();

        player.getPlayerSprite().setRegion(animation.getKeyFrame(player.getTime()));

        if (!animation.isAnimationFinished(player.getTime())) {
            player.setTime(player.getTime() + Gdx.graphics.getDeltaTime());
        }
        else {
            player.setTime(0);
        }

        animation.setPlayMode(Animation.PlayMode.LOOP);
    }

    public Character getPlayer() {
        return player;
    }

    public void setPlayer(Character player) {
        this.player = player;
    }
}
