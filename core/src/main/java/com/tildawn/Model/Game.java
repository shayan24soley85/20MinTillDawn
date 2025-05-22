package com.tildawn.Model;

public class Game {
    private Boolean grayscaleToggle=false;
    private Boolean autoReload=false;
    private Boolean sfxToggle=true;
    public Boolean getGrayscaleToggle() {
        return grayscaleToggle;
    }

    public void setGrayscaleToggle(Boolean grayscaleToggle) {
        this.grayscaleToggle = grayscaleToggle;
    }

    public Boolean getAutoReload() {
        return autoReload;
    }

    public void setAutoReload(Boolean autoReload) {
        this.autoReload = autoReload;
    }

    public Boolean getSfxToggle() {
        return sfxToggle;
    }

    public void setSfxToggle(Boolean sfxToggle) {
        this.sfxToggle = sfxToggle;
    }
}
