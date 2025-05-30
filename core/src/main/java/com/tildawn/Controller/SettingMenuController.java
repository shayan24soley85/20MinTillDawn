package com.tildawn.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.tildawn.Enums.SFX;
import com.tildawn.Main;
import com.tildawn.Model.Game;
import com.tildawn.Model.GameAssetManager;
import com.tildawn.Model.Language.Language;
import com.tildawn.View.MainMenuView;
import com.tildawn.View.PauseMenuView;
import com.tildawn.View.SettingMenuView;

public class SettingMenuController {
    private SettingMenuView view;

    public void setView(SettingMenuView view) {
        this.view = view;
    }

    public SettingMenuView getView() {
        return view;
    }
    public void handleButtonClick() {
        if(view!=null){
            if(view.getBackButton().isChecked()){
                SFX.CLICK_BUTTON.play();
                view.getBackButton().setChecked(false);
                if (Main.getMain().getApp().getCurrentGame().isInPause()) {
                    Main.getMain().setScreen(new PauseMenuView
                        (new PauseMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
                    return;
                }
                Main.getMain().setScreen(new MainMenuView
                    (new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            } else if (view.getConfirmButton().isChecked()) {
                SFX.CLICK_BUTTON.play();
                view.getConfirmButton().setChecked(false);
                if(view.getSfxToggle().isChecked()){
                    for(SFX sfx:SFX.values()){
                        sfx.load();
                    }
                }else {
                    for(SFX sfx:SFX.values()){
                        sfx.dispose();
                    }
                }
                Game game=Main.getMain().getApp().getNewGame();
                if(Main.getMain().getApp().getCurrentGame().isInPause()){
                    game=Main.getMain().getApp().getCurrentGame();
                }

                game.setAutoReload(view.getAutoReload().isChecked());
                game.setSfxToggle(view.getSfxToggle().isChecked());

                game.setGrayscaleToggle(view.getGrayscaleToggle().isChecked());

                Main.getBatch().setShader(view.getGrayscaleToggle().isChecked()
                    ? Main.getMain().getGrayscaleShader()
                    : null);
                game.setGrayscaleToggle(view.getGrayscaleToggle().isChecked());
                Main.getMain().getApp().getCurrentLanguage().setCurrentLanguage(view.getLanguage().getSelected().equals("ENGLISH")? Language.English:Language.French);
                Music music = Main.getMain().getApp().getCurrentMusic();
                String selectedPath = view.getMusicSelect().getSelected().getPath();

                if (selectedPath.equals("Stop the music")) {
                    if (music != null) {
                        music.stop();
                        music.dispose();
                        Main.getMain().getApp().setCurrentMusic(null);
                        Main.getMain().getApp().setCurrentMusicPath(null);
                    }
                } else {
                    String currentMusicPath = Main.getMain().getApp().getCurrentMusicPath();


                    if (music == null || currentMusicPath == null ||
                        currentMusicPath.equals("Stop the music") ||
                        !currentMusicPath.equals(selectedPath)) {


                        if (music != null) {
                            music.stop();
                            music.dispose();
                        }


                        music = Gdx.audio.newMusic(Gdx.files.internal(selectedPath));
                        music.setLooping(true);
                        music.setVolume(view.getVolumeSlider().getValue());

                        Main.getMain().getApp().setCurrentMusic(music);
                        Main.getMain().getApp().setCurrentMusicPath(selectedPath);

                        music.play();
                    } else {
                        music.setVolume(view.getVolumeSlider().getValue());
                    }
                }
            } else if (view.getSetButtons().isChecked()) {
                SFX.CLICK_BUTTON.play();
                view.getSetButtons().setChecked(false);
                //todo go to change button menu
            }
        }
    }
}
