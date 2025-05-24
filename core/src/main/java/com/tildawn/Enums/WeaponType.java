package com.tildawn.Enums;

import com.badlogic.gdx.graphics.Texture;
import com.tildawn.Main;
import com.tildawn.Model.Character;

import java.util.HashMap;

public enum WeaponType {
    shotgun(2,10,1,4,new Texture("Texture2D/T/T_DualShotgun_Gun.png")),
    revolver(6,20,1,1,new Texture("Texture2D/T/T_Volley_Gun.png")),
    dualSmg(24,8,2,1,new Texture("smg/T_DualSMGs_Icon.png"));
    private final int maxAmmo;
    private final int damage;
    private final int reloadTime;
    private final int projectTile;
    private final Texture texture ;
    WeaponType(int maxAmmo, int damage, int reloadTime, int projectTile,Texture texture) {
        this.maxAmmo = maxAmmo;
        this.damage = damage;
        this.reloadTime = reloadTime;
        this.projectTile = projectTile;
        this.texture=texture;
    }

    public Texture getTexture() {
        return texture;
    }

    public int getMaxAmmo() {
        Character character= Main.getMain().getApp().getCurrentGame().getCharacter();
        if (character==null){
            return maxAmmo;
        }
        return character.getAbilities().containsKey(AbilityType.AMOCREASE)&&character.getAbilities().get(AbilityType.AMOCREASE).isEnabled()?maxAmmo+5:maxAmmo;
    }

    public int getDamage() {

        Character character= Main.getMain().getApp().getCurrentGame().getCharacter();
        if (character==null){
            return damage;
        }
        return character.getAbilities().containsKey(AbilityType.DAMAGER)&&character.getAbilities().get(AbilityType.DAMAGER).isEnabled()?(int)(damage*1.25):damage;
    }

    public int getReloadTime() {
        return reloadTime;
    }

    public int getProjectTile() {

        Character character= Main.getMain().getApp().getCurrentGame().getCharacter();
        if (character==null){
            return projectTile;
        }
        return character.getAbilities().containsKey(AbilityType.PROCREASE)&&character.getAbilities().get(AbilityType.PROCREASE).isEnabled()?projectTile+1:projectTile;
    }
}
