package com.tildawn.Model;

import com.tildawn.Enums.WeaponType;

public class Weapon {
    private WeaponType type;

    public Weapon(WeaponType type) {
        this.type = type;
    }

    public WeaponType getType() {
        return type;
    }

    public void setType(WeaponType type) {
        this.type = type;
    }
}
