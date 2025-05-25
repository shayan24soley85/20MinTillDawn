package com.tildawn.Enums;

import com.badlogic.gdx.graphics.Texture;
import com.tildawn.Main;
import com.tildawn.Model.Character;

public enum CharacterType {
    Shana(4,4,"Shana",new Texture("Sprite/Idle/Idle_4 #8316.png")),
    Diamond(7,1,"Diamond",new Texture("Sprite/Idle/Idle_0 #8326.png")),
    Scarlet(3,5,"Scarlet",new Texture("Sprite/Idle/Idle_0 #8329.png")),
    Lilith(5,3,"Lilith",new Texture("Sprite/Idle/Idle_0 #8333.png")),
    Dasher(2,10,"Dasher",new Texture("Sprite/Idle/Idle_5 #8302.png")),;
    private final int maxHp;
    private final int speed;
    private final String name;
    private Texture texture;

    CharacterType(int maxHp, int speed,String name,Texture texture) {
        this.maxHp = maxHp;
        this.speed = speed;
        this.name = name;
        this.texture = texture;
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

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
}
