package com.tildawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tildawn.Enums.AbilityType;
import com.tildawn.Enums.CharacterType;
import com.tildawn.Enums.SFX;

import java.util.HashMap;
import java.util.Map;

public class Character {
    private CharacterType type;
    private int xp;
    private int level;
    private Weapon weapon;
    private int hp;
    private Boolean isInvincible;
    private Map<AbilityType,Ability> abilities;
    private int eliminations;
    private float posX;
    private float posY;
    private Texture playerTexture = new Texture(GameAssetManager.getGameAssetManager().getCharacter1_idle0());
    private Sprite playerSprite = new Sprite(playerTexture);
    private boolean isPlayerIdle = true;
    private boolean isPlayerRunning = false;
    private float time = 0;
    private CollisionRect rect ;
    public Character(CharacterType type, Weapon weapon) {
        this.type = type;
        this.weapon = weapon;
        abilities = new HashMap<>();
        hp=type.getMaxHp();
        level=1;
        xp=0;
        eliminations=0;
        playerSprite.setPosition((float) Gdx.graphics.getWidth() / 2, (float) Gdx.graphics.getHeight() / 2);
        playerSprite.setSize(playerTexture.getWidth() * 3, playerTexture.getHeight() * 3);
        rect = new CollisionRect((float) Gdx.graphics.getWidth() / 2, (float) Gdx.graphics.getHeight(), playerTexture.getWidth() * 3, playerTexture.getHeight() * 3);
    }
    public int xpToNextLevel(int level) {
        return level*20;
    }

   public boolean increaseXp(int amount) {
        xp+=amount;
        if(xp>=maxLevelXp()+xpToNextLevel(level)){
            level++;
            SFX.LEVEL.play();
            return true;
        }
        return false;
   }

    public int maxLevelXp(){
        if(level==1){
            return 0;
        } else if (level==2) {
            return 20;
        }else if (level==3) {
            return 60;
        }else if (level==4) {
           return  120;
        } else if (level==5) {
            return 200;
        }else if (level==6) {
            return 300;
        }else if (level==7) {
            return 420;
        }else if (level==8) {
            return 560;
        }else if (level==9) {
            return 720;
        }else if (level==10) {
            return 900;
        } else if (level==11) {
            return 1100;
        } else if (level==12) {
            return 1320;
        } else if (level==13) {
            return 1560;
        } else if (level==14) {
            return 1820;
        }
        return 2000;
    }
    public CollisionRect getRect() {
        return rect;
    }

    public void setRect(CollisionRect rect) {
        this.rect = rect;
    }

    public boolean isPlayerIdle() {
        return isPlayerIdle;
    }

    public void setPlayerIdle(boolean playerIdle) {
        isPlayerIdle = playerIdle;
    }

    public boolean isPlayerRunning() {
        return isPlayerRunning;
    }

    public void setPlayerRunning(boolean playerRunning) {
        isPlayerRunning = playerRunning;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
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

        return abilities.containsKey(AbilityType.VITALITY)&&abilities.get(AbilityType.VITALITY).isEnabled()?(hp+1):hp;
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

    public Map<AbilityType, Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(Map<AbilityType, Ability> abilities) {
        this.abilities = abilities;
    }

    public int getEliminations() {
        return eliminations;
    }

    public void setEliminations(int eliminations) {
        this.eliminations = eliminations;
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public Texture getPlayerTexture() {
        return playerTexture;
    }

    public void setPlayerTexture(Texture playerTexture) {
        this.playerTexture = playerTexture;
    }

    public Sprite getPlayerSprite() {
        return playerSprite;
    }

    public void setPlayerSprite(Sprite playerSprite) {
        this.playerSprite = playerSprite;
    }
}
