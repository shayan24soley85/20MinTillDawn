package com.tildawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tildawn.Controller.MainMenuController;
import com.tildawn.Main;
import com.tildawn.Model.User;

public class MainMenuView implements Screen {
    private Stage stage;
    private final TextButton playButton;
    private final Label gameTitle;
    private final TextField field;
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

    private Label nameLabel ;
    private Label scoreLabel ;
    public MainMenuView(MainMenuController controller, Skin skin) {
        this.playButton = new TextButton("Play", skin);
        this.gameTitle = new Label("this is a title", skin);
        this.field = new TextField("this is a field", skin);
        this.controller = controller;
        this.table = new Table();

         nameLabel = new Label("Username: " +user.getUsername(), skin);
        scoreLabel = new Label("Score: " + user.getScore(), skin);

         continueButton = new TextButton("Continue Game", skin);
         settingsButton = new TextButton("Settings", skin);
         profileButton = new TextButton("Profile", skin);
         preGameButton = new TextButton("Pre-Game Menu", skin);
         scoreboardButton = new TextButton("Scoreboard", skin);
         talentButton = new TextButton("Talent Menu", skin);
         logoutButton = new TextButton("Logout", skin);
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
        avatarImage.setSize(64, 64); // سایز دلخواه
        avatarImage.setScaling(Scaling.fit);





        Table topTable = new Table();
        topTable.add(avatarImage).size(64).padRight(10);
        topTable.add(nameLabel).left().padRight(20);
        topTable.add(scoreLabel).left();
        topTable.top().left();

        Table menuTable = new Table();
        menuTable.defaults().width(500).height(100).pad(10);
        menuTable.add(continueButton).row();
        menuTable.add(settingsButton).row();
        menuTable.add(profileButton).row();
        menuTable.add(preGameButton).row();
        menuTable.add(scoreboardButton).row();
        menuTable.add(talentButton).row();
        menuTable.add(logoutButton).row();

        rootTable.top().padTop(30);
        rootTable.add(topTable).expandX().left().padLeft(20).row();
        rootTable.add(menuTable).expand().center();
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
    }

    public TextButton getPlayButton() {
        return playButton;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Label getGameTitle() {
        return gameTitle;
    }

    public TextField getField() {
        return field;
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
}
