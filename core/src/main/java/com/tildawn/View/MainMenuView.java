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
import com.tildawn.Main;
import com.tildawn.Model.User;

public class MainMenuView implements Screen {
    private Stage stage;
    public Table table;
    private final MainMenuController controller;
    private final User user=Main.getMain().getApp().getCurrentUser();
    private TextButton continueButton ;
    private TextButton settingsButton ;
    private TextButton profileButton ;
    private TextButton preGameButton ;
    private TextButton scoreboardButton ;
    private TextButton talentButton ;
    private TextButton logoutButton ;
    private final Label errorLabel;
    private Timer.Task clearErrorTask;
    private final Label SuccessMessageLabel;
    private Timer.Task clearErrorTask2;

    private Label nameLabel ;
    private Label scoreLabel ;
    public MainMenuView(MainMenuController controller, Skin skin) {
        this.controller = controller;
        this.table = new Table();
        this.controller.setView(this);

         nameLabel = new Label(com.tildawn.Enums.Label.USERNAME +user.getUsername(), skin);
        scoreLabel = new Label(com.tildawn.Enums.Label.SCORE.getText() + user.getScore(), skin);

         continueButton = new TextButton(com.tildawn.Enums.Label.CONTINUE_GAME.getText(), skin);
         settingsButton = new TextButton(com.tildawn.Enums.Label.SETTINGS.getText(), skin);
         profileButton = new TextButton(com.tildawn.Enums.Label.PROFILE.getText(), skin);
         preGameButton = new TextButton(com.tildawn.Enums.Label.PRE_GAME_MENU.getText(), skin);
         scoreboardButton = new TextButton(com.tildawn.Enums.Label.SCOREBOARD.getText(), skin);
         talentButton = new TextButton(com.tildawn.Enums.Label.HINT_MENU.getText(), skin);
         logoutButton = new TextButton(com.tildawn.Enums.Label.LOGOUT_LOGIN_MENU.getText(), skin);

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
        Texture backgroundTexture = new Texture(Gdx.files.internal("backgrounds/36.png"));
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
        menuTable.add(continueButton).row();
        menuTable.add(settingsButton).row();
        menuTable.add(profileButton).row();
        menuTable.add(preGameButton).row();
        menuTable.add(scoreboardButton).row();
        menuTable.add(talentButton).row();
        menuTable.add(logoutButton).row();
       menuTable.add(errorLabel).row();
       menuTable.add(SuccessMessageLabel).row();

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
        controller.handleMainMenuButtons();
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

    public MainMenuController getController() {
        return controller;
    }

    public User getUser() {
        return user;
    }

    public TextButton getContinueButton() {
        return continueButton;
    }

    public void setContinueButton(TextButton continueButton) {
        this.continueButton = continueButton;
    }

    public TextButton getSettingsButton() {
        return settingsButton;
    }

    public void setSettingsButton(TextButton settingsButton) {
        this.settingsButton = settingsButton;
    }

    public TextButton getProfileButton() {
        return profileButton;
    }

    public void setProfileButton(TextButton profileButton) {
        this.profileButton = profileButton;
    }

    public TextButton getPreGameButton() {
        return preGameButton;
    }

    public void setPreGameButton(TextButton preGameButton) {
        this.preGameButton = preGameButton;
    }

    public TextButton getScoreboardButton() {
        return scoreboardButton;
    }

    public void setScoreboardButton(TextButton scoreboardButton) {
        this.scoreboardButton = scoreboardButton;
    }

    public TextButton getTalentButton() {
        return talentButton;
    }

    public void setTalentButton(TextButton talentButton) {
        this.talentButton = talentButton;
    }

    public TextButton getLogoutButton() {
        return logoutButton;
    }

    public void setLogoutButton(TextButton logoutButton) {
        this.logoutButton = logoutButton;
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
}
