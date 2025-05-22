package com.tildawn.Model;

import com.tildawn.Enums.CharacterType;

import java.util.HashMap;
import java.util.Map;

public class Character {
    private CharacterType type;
    private int xp;
    private int level;
    private Weapon weapon;
    private int hp;
    private Boolean isInvincible;
    private Map<String,Ability> abilities=new HashMap<String,Ability>();


    public Character(CharacterType type, Weapon weapon, Map<String, Ability> abilities) {
        this.type = type;
        this.weapon = weapon;
        this.abilities = abilities;
        hp=type.getMaxHp();
        level=1;
        xp=0;
    }

    public CharacterType getType() {
        return type;
    }

    public void setType(CharacterType type) {
        this.type = type;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public Boolean getInvincible() {
        return isInvincible;
    }

    public void setInvincible(Boolean invincible) {
        isInvincible = invincible;
    }

    public Map<String, Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(Map<String, Ability> abilities) {
        this.abilities = abilities;
    }
}
