package com.tildawn.Enums;

import com.badlogic.gdx.graphics.Texture;

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
        return maxAmmo;
    }

    public int getDamage() {
        return damage;
    }

    public int getReloadTime() {
        return reloadTime;
    }

    public int getProjectTile() {
        return projectTile;
    }
}
