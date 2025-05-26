package com.tildawn.Enums;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public enum SFX {
    SHOOT("sfx/single_shot.wav"),
    RELOAD("sfx/Weapon_Shotgun_Reload.wav"),
    FOOT_STEP("sfx/Footsteps_Casual_Grass_02.wav"),
    CLICK_BUTTON("sfx/UI Click 36.wav"),
    NEED_AMMO("sfx/empty-gun-shot-6209 (1).mp3"),
    LEVEL("sfx/game-level-complete-143022.mp3"),
    WINNING("sfx/You Win (2).wav"),
    LOSING("sfx/You Lose (4).wav"),
    collectCoin("sfx/Coins (10).wav"),
    monsterDying("sfx/Monster_2_RecieveAttack_HighIntensity_01.wav/"),
    lowHealth("sfx/sfx_lowhealth_alarmloop1.wav");


    private final String path;
    private Sound sound;

    SFX(String path) {
        this.path = path;
    }

    public void load() {
            sound = Gdx.audio.newSound(Gdx.files.internal(path));
    }

    public void play() {
        if (sound != null)
            sound.play();
    }

    public void play(float volume) {
        if (sound != null)
            sound.play(volume);
    }

    public void dispose() {
        if (sound != null)
            sound.dispose();
    }
}
