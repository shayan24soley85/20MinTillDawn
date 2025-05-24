package com.tildawn.Enums;

import com.tildawn.Main;
import com.tildawn.Model.Character;

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
        Character character= Main.getMain().getApp().getCurrentGame().getCharacter();
        if (character==null){
            return speed;
        }
        return character.getAbilities().containsKey(AbilityType.SPEEDY)&&character.getAbilities().get(AbilityType.SPEEDY).isEnabled()?speed*2:speed;
    }
    public String getName() {
        return name;
    }


    public String print() {
        return "\n"+Label.MAXHP+"=" + maxHp +
            "\n"+Label.SPEED+"=" + speed ;
    }
}
