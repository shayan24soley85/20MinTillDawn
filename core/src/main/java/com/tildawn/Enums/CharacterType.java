package com.tildawn.Enums;

public enum CharacterType {
    Shana(4,4,"Shana"),
    Diamond(7,1,"Diamond"),
    Scarlet(3,5,"Scarlet"),
    Lilith(5,3,"Lilith"),
    Dasher(2,10,"Dasher"),;
    private final int maxHp;
    private final int speed;
    private final String name;

    CharacterType(int maxHp, int speed,String name) {
        this.maxHp = maxHp;
        this.speed = speed;
        this.name = name;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getSpeed() {
        return speed;
    }
    public String getName() {
        return name;
    }
}
