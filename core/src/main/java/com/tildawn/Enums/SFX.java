package com.tildawn.Enums;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public enum SFX {
    SHOOT("sfx/single_shot.wav");
//    RELOAD("sfx/reload.wav"),
//    EXPLOSION("sfx/explosion.wav"),
//    BUTTON_CLICK("sfx/button_click.wav");

    private final String path;
    private Sound sound;

    SFX(String path) {
        this.path = path;
    }

    public void load() {
        if (sound == null)
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
