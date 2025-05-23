package com.tildawn.Model;

import com.tildawn.Enums.WeaponType;

public class Weapon {
    private WeaponType type;
    private int ammo;

    public Weapon(WeaponType type) {
        this.type = type;
        ammo = type.getMaxAmmo();
    }

    public WeaponType getType() {
        return type;
    }

    public void setType(WeaponType type) {
        this.type = type;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }
}
