package com.tildawn.Enums;

public enum WeaponType {
    shotgun(2,10,1,4),
    revolver(6,20,1,1),
    dualSmg(24,8,2,1);
    private final int maxAmmo;
    private final int damage;
    private final int reloadTime;
    private final int projectTile;

    WeaponType(int maxAmmo, int damage, int reloadTime, int projectTile) {
        this.maxAmmo = maxAmmo;
        this.damage = damage;
        this.reloadTime = reloadTime;
        this.projectTile = projectTile;
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
