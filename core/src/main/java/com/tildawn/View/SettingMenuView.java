package com.tildawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tildawn.Controller.SettingMenuController;
import com.tildawn.Enums.music;
import com.tildawn.Main;
import com.tildawn.Model.Language.Language;

public class SettingMenuView implements Screen {
    private Stage stage;
    private final SettingMenuController controller;
    public Table table;
    private final Skin skin;
    private Label musicLabel ;
    private Label languageLabel ;
    private SelectBox<music> musicSelect;

    private Label volumeLabel ;
    private Slider volumeSlider ;


    private CheckBox sfxToggle;


   private Label controlLabel ;
   private TextButton setButtons ;
   private TextButton backButton ;
   private TextButton confirmButton ;
   private final SelectBox<String> language;

    private CheckBox autoReload ;


   private CheckBox grayscaleToggle ;
    private final Label errorLabel;
    private Timer.Task clearErrorTask;

    public SettingMenuView(SettingMenuController controller, Skin skin) {
        this.controller = controller;
        controller.setView(this);
        this.skin = skin;
        errorLabel = new Label("", skin);
        languageLabel = new Label(com.tildawn.Enums.Label.GAME_LANGUAGE.getText(), skin);
        errorLabel.setColor(Color.RED);
        errorLabel.setWrap(true);
        musicLabel = new Label(com.tildawn.Enums.Label.BACKGROUND_MUSIC.getText(), skin);
         musicSelect = new SelectBox<>(skin);
        musicSelect.setItems(music.values());
        this.table = new Table();
         volumeLabel = new Label(com.tildawn.Enums.Label.MUSIC_VOLUME.getText(), skin);
       volumeSlider = new Slider(0f, 1f, 0.01f, false, skin);
       language = new SelectBox<>(skin);
       language.setItems("ENGLISH","FRENCH");
        language.setSelected(Main.getMain().getApp().getCurrentLanguage().
            getCurrentLanguage().equals(Language.English)?"ENGLISH":"FRENCH");

        this.confirmButton = new TextButton(com.tildawn.Enums.Label.CONFIRM.getText(), skin);
        this.backButton = new TextButton(com.tildawn.Enums.Label.BACK.getText(), skin);
         sfxToggle = new CheckBox(com.tildawn.Enums.Label.ENABLE_SFX.getText(), skin);

         sfxToggle.setChecked(true);
         controlLabel = new Label(com.tildawn.Enums.Label.CHANGE_CONTROLS.getText(), skin);
         setButtons = new TextButton(com.tildawn.Enums.Label.KEYBOARD_SETTING.getText(), skin);


         autoReload = new CheckBox(com.tildawn.Enums.Label.AUTO_RELOAD.getText(), skin);


         grayscaleToggle = new CheckBox(com.tildawn.Enums.Label.GRAYSCALE_MODE.getText(), skin);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());

        Texture backgroundTexture = new Texture(Gdx.files.internal("backgrounds/33.png"));
        Image backgroundImage = new Image(backgroundTexture);

        backgroundImage.setFillParent(true);

        stage.addActor(backgroundImage);
        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.center();
        table.add(controlLabel).padBottom(20).left();
        table.row();
        table.row();
        table.row();
        table.add(setButtons).padBottom(50).width(500);
        table.row();
        table.row();
        table.row();
        table.add(musicLabel).padBottom(20).left();
        table.row();
        table.add(musicSelect).padBottom(20).width(400);
        table.row();

        table.add(volumeLabel).padBottom(20).left();
        table.row();
        table.add(volumeSlider).padBottom(40).width(400);
        table.row();

        table.add(sfxToggle).padBottom(30).left();
        table.row();
        table.row();
        table.row();
        table.add(autoReload).padBottom(30).left();
        table.row();

        table.add(grayscaleToggle).padBottom(30).left();
        table.row();
        table.add(languageLabel).padBottom(20).left();
        table.row();
        table.add(language).padBottom(20).width(400);
        table.row();
        Table buttonRow = new Table();
        buttonRow.add(backButton).padRight(20);
        buttonRow.add(confirmButton);

        table.add(buttonRow).padBottom(30);

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handleButtonClick();
    }

    public void setErrorMessage(String error) {
        errorLabel.setText(error);

        if (clearErrorTask != null) {
            clearErrorTask.cancel();
        }

        if (!error.isEmpty()) {
            clearErrorTask = new Timer.Task() {
                @Override
                public void run() {
                    errorLabel.setText("");
                }
            };
            Timer.schedule(clearErrorTask, 2);
        }
    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override
    public void dispose() {
        stage.dispose();
        if (clearErrorTask != null) clearErrorTask.cancel();
    }



    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }





    public SettingMenuController getController() {
        return controller;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Skin getSkin() {
        return skin;
    }

    public Label getErrorLabel() {
        return errorLabel;
    }

    public Timer.Task getClearErrorTask() {
        return clearErrorTask;
    }

    public void setClearErrorTask(Timer.Task clearErrorTask) {
        this.clearErrorTask = clearErrorTask;
    }

    public Label getMusicLabel() {
        return musicLabel;
    }

    public void setMusicLabel(Label musicLabel) {
        this.musicLabel = musicLabel;
    }

    public SelectBox<music> getMusicSelect() {
        return musicSelect;
    }

    public void setMusicSelect(SelectBox<music> musicSelect) {
        this.musicSelect = musicSelect;
    }

    public Label getVolumeLabel() {
        return volumeLabel;
    }

    public void setVolumeLabel(Label volumeLabel) {
        this.volumeLabel = volumeLabel;
    }

    public Slider getVolumeSlider() {
        return volumeSlider;
    }

    public void setVolumeSlider(Slider volumeSlider) {
        this.volumeSlider = volumeSlider;
    }

    public CheckBox getSfxToggle() {
        return sfxToggle;
    }

    public void setSfxToggle(CheckBox sfxToggle) {
        this.sfxToggle = sfxToggle;
    }

    public Label getControlLabel() {
        return controlLabel;
    }

    public void setControlLabel(Label controlLabel) {
        this.controlLabel = controlLabel;
    }

    public TextButton getSetShoot() {
        return setButtons;
    }

    public void setSetShoot(TextButton setButtons) {
        this.setButtons = setButtons;
    }

    public CheckBox getAutoReload() {
        return autoReload;
    }

    public void setAutoReload(CheckBox autoReload) {
        this.autoReload = autoReload;
    }

    public CheckBox getGrayscaleToggle() {
        return grayscaleToggle;
    }

    public void setGrayscaleToggle(CheckBox grayscaleToggle) {
        this.grayscaleToggle = grayscaleToggle;
    }

    public TextButton getSetButtons() {
        return setButtons;
    }

    public void setSetButtons(TextButton setButtons) {
        this.setButtons = setButtons;
    }

    public TextButton getBackButton() {
        return backButton;
    }

    public void setBackButton(TextButton backButton) {
        this.backButton = backButton;
    }

    public TextButton getConfirmButton() {
        return confirmButton;
    }

    public void setConfirmButton(TextButton confirmButton) {
        this.confirmButton = confirmButton;
    }

    public SelectBox<String> getLanguage() {
        return language;
    }
}
