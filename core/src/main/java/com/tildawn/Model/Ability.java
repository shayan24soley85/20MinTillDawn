package com.tildawn.Model;

import com.tildawn.Enums.AbilityType;
import com.tildawn.Main;

public class Ability {
    private final AbilityType abilityType;
    private boolean enabled;
    private float elapsedTime;
    private final float duration;

    public Ability(AbilityType abilityType, float durationSeconds) {
        this.abilityType = abilityType;
        this.duration = durationSeconds;
        this.elapsedTime = 0;
        this.enabled = true;
    }

    public void update(float deltaTime) {
        if (Main.getMain().getApp().getCurrentGame().isInPause() || !enabled) return;

        elapsedTime += 1000*deltaTime;
        if (elapsedTime >= duration) {
            enabled = false;
        }
    }

    public AbilityType getAbilityType() {
        return abilityType;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void reset() {
        elapsedTime = 0;
        enabled = true;
    }

    @Override
    public String toString() {
        return "name:"+abilityType.getName()+"\ndescription:"+abilityType.getDescription();
    }
}
