package com.tildawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tildawn.Controller.MainMenuController;
import com.tildawn.Controller.PauseMenuController;
import com.tildawn.Main;
import com.tildawn.Model.User;

public class PauseMenuView implements Screen {
    private Stage stage;
    public Table table;
    private final PauseMenuController controller;
    private final User user=Main.getMain().getApp().getCurrentUser();
    private TextButton ResumeButton ;
    private TextButton settingsButton ;
    private TextButton currentAbilities ;
    private TextButton giveUpButton ;
    private TextButton hintMenuButton ;
    private TextButton saveAndExit ;
    private final Label errorLabel;
    private Timer.Task clearErrorTask;
    private final Label SuccessMessageLabel;
    private Timer.Task clearErrorTask2;

    private Label nameLabel ;
    private Label scoreLabel ;
    public PauseMenuView(PauseMenuController controller, Skin skin) {
        this.controller = controller;
        this.table = new Table();
        this.controller.setView(this);

        nameLabel = new Label(com.tildawn.Enums.Label.USERNAME +user.getUsername(), skin);
        scoreLabel = new Label(com.tildawn.Enums.Label.SCORE.getText() + user.getScore(), skin);

        ResumeButton = new TextButton(com.tildawn.Enums.Label.RESUME_GAME.getText(), skin);
        settingsButton = new TextButton(com.tildawn.Enums.Label.SETTINGS.getText(), skin);
        currentAbilities = new TextButton(com.tildawn.Enums.Label.AVAILABLE_ABILITIES.getText(), skin);
        giveUpButton = new TextButton(com.tildawn.Enums.Label.GIVE_UP.getText(), skin);
        hintMenuButton = new TextButton(com.tildawn.Enums.Label.HINT_MENU.getText(), skin);
        saveAndExit = new TextButton(com.tildawn.Enums.Label.SAVE_AND_EXIT.getText(), skin);

        errorLabel = new Label("", skin);
        errorLabel.setColor(Color.RED);
        errorLabel.setWrap(true);
        SuccessMessageLabel = new Label("", skin);
        SuccessMessageLabel.setColor(Color.GREEN);
        SuccessMessageLabel.setWrap(true);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        Texture backgroundTexture = new Texture(Gdx.files.internal("backgrounds/19.png"));
        Image backgroundImage = new Image(backgroundTexture);

        backgroundImage.setFillParent(true);

        stage.addActor(backgroundImage);
        stage.addActor(table);
        table.setFillParent(true);



        Table rootTable = new Table();
        rootTable.setFillParent(true);
        stage.addActor(rootTable);


        Texture avatarTexture = new Texture(Gdx.files.internal(Main.getMain().getApp().getCurrentUser().getAvatarPath()));
        Image avatarImage = new Image(new TextureRegionDrawable(new TextureRegion(avatarTexture)));
        avatarImage.setSize(64, 64);
        avatarImage.setScaling(Scaling.fit);





        Table topTable = new Table();
        topTable.add(avatarImage).size(64).padRight(10);
        topTable.add(nameLabel).left().padRight(20);
        topTable.add(scoreLabel).left();
        topTable.top().left();

        Table menuTable = new Table();
        menuTable.defaults().width(500).height(90).pad(10);
        menuTable.add(ResumeButton).row();
        menuTable.add(settingsButton).row();
        menuTable.add(currentAbilities).row();
        menuTable.add(giveUpButton).row();
        menuTable.add(hintMenuButton).row();
        menuTable.add(saveAndExit).row();
        menuTable.add(errorLabel).row();
        menuTable.add(SuccessMessageLabel).row();
        //todo  error bad 2 saaniye nmire

        rootTable.top().padTop(0);
        rootTable.add(topTable).expandX().left().padLeft(20).row();
        rootTable.add(menuTable).expand().center();
        table.row().pad(10, 370, 0, 0);
        table.row().pad(10, 370, 0, 0);
        //table.add(errorLabel).colspan(2).left().width(600);
        stage.addActor(table);
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handleButtonPressed();
    }
    public void setErrorMessage(String error) {
        errorLabel.setText(error);

        errorLabel.clearActions();

        if (!error.isEmpty()) {
            errorLabel.addAction(Actions.sequence(
                Actions.delay(2),
                Actions.run(() -> {
                    errorLabel.setText("");
                    System.out.println("ERROR CLEARED via Action!");
                })
            ));
        }
    }
    public void setSuccessMessage(String message) {
        SuccessMessageLabel.setText(message);
        if (clearErrorTask2 != null) {
            clearErrorTask2.cancel();
        }
        clearErrorTask2 = new Timer.Task() {
            @Override
            public void run() {
                SuccessMessageLabel.setText("");
            }
        };
        Timer.schedule(clearErrorTask2, 5);
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

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


    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public PauseMenuController getController() {
        return controller;
    }

    public User getUser() {
        return user;
    }

    public TextButton getSettingsButton() {
        return settingsButton;
    }

    public void setSettingsButton(TextButton settingsButton) {
        this.settingsButton = settingsButton;
    }

    public TextButton getTalentButton() {
        return hintMenuButton;
    }

    public void setTalentButton(TextButton hintMenuButton) {
        this.hintMenuButton = hintMenuButton;
    }

    public Label getNameLabel() {
        return nameLabel;
    }

    public void setNameLabel(Label nameLabel) {
        this.nameLabel = nameLabel;
    }

    public Label getScoreLabel() {
        return scoreLabel;
    }

    public void setScoreLabel(Label scoreLabel) {
        this.scoreLabel = scoreLabel;
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

    public Label getSuccessMessageLabel() {
        return SuccessMessageLabel;
    }

    public Timer.Task getClearErrorTask2() {
        return clearErrorTask2;
    }

    public void setClearErrorTask2(Timer.Task clearErrorTask2) {
        this.clearErrorTask2 = clearErrorTask2;
    }

    public TextButton getResumeButton() {
        return ResumeButton;
    }

    public void setResumeButton(TextButton resumeButton) {
        ResumeButton = resumeButton;
    }

    public TextButton getCurrentAbilities() {
        return currentAbilities;
    }

    public void setCurrentAbilities(TextButton currentAbilities) {
        this.currentAbilities = currentAbilities;
    }

    public TextButton getGiveUpButton() {
        return giveUpButton;
    }

    public void setGiveUpButton(TextButton giveUpButton) {
        this.giveUpButton = giveUpButton;
    }

    public TextButton getHintMenuButton() {
        return hintMenuButton;
    }

    public void setHintMenuButton(TextButton hintMenuButton) {
        this.hintMenuButton = hintMenuButton;
    }

    public TextButton getSaveAndExit() {
        return saveAndExit;
    }

    public void setSaveAndExit(TextButton saveAndExit) {
        this.saveAndExit = saveAndExit;
    }
}
