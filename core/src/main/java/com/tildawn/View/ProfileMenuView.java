package com.tildawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tildawn.Controller.ProfileMenuController;
import com.tildawn.Main;
import com.tildawn.Model.User;

public class ProfileMenuView implements Screen {
    private Stage stage;
    public Table table;
    private final ProfileMenuController controller;
    private TextButton changeUsernameButton ;
    private TextButton changePasswordButton ;
    private TextButton deleteAccountButton ;
    private TextButton changeProfileButton ;
    private TextButton backButton ;
    private TextField username ;
    private TextField password ;
    private Label nameLabel ;
    private Label scoreLabel ;
    public ProfileMenuView(ProfileMenuController controller, Skin skin) {
        this.controller = controller;
        this.table = new Table();
        this.controller.setView(this);
        changeUsernameButton = new TextButton("Change Username", skin);
        changePasswordButton = new TextButton("Change Password", skin);
        deleteAccountButton = new TextButton("Delete Account", skin);
        changeProfileButton = new TextButton("Change Avatar", skin);
        backButton = new TextButton("Back", skin);
        username = new TextField(Main.getMain().getApp().getCurrentUser().getUsername(), skin);
        password = new TextField(Main.getMain().getApp().getCurrentUser().getPassword(), skin);
        password.setPasswordCharacter('*');
        password.setPasswordMode(true);
        nameLabel = new Label("Username: " +Main.getMain().getApp().getCurrentUser().getUsername(), skin);
        nameLabel.setColor(Color.CYAN);
        scoreLabel = new Label("Score: " + Main.getMain().getApp().getCurrentUser().getScore(), skin);
        scoreLabel.setColor(Color.CYAN);

    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        Texture backgroundTexture = new Texture(Gdx.files.internal("backgrounds/34.png"));
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
        menuTable.defaults().width(500).height(100).pad(10);
        menuTable.add(changeUsernameButton);
        menuTable.add(username).row();
        menuTable.add(changePasswordButton);
        menuTable.add(password).row();
        menuTable.add(deleteAccountButton).row();
        menuTable.add(changeProfileButton).row();
        menuTable.add(backButton).row();

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
        //controller.handleMainMenuButtons();
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

    public ProfileMenuController getController() {
        return controller;
    }


    public TextButton getContinueButton() {
        return changeUsernameButton;
    }

    public void setContinueButton(TextButton changeUsernameButton) {
        this.changeUsernameButton = changeUsernameButton;
    }

    public TextButton getSettingsButton() {
        return changePasswordButton;
    }

    public void setSettingsButton(TextButton changePasswordButton) {
        this.changePasswordButton = changePasswordButton;
    }

    public TextButton getProfileButton() {
        return deleteAccountButton;
    }

    public void setProfileButton(TextButton deleteAccountButton) {
        this.deleteAccountButton = deleteAccountButton;
    }

    public TextButton getPreGameButton() {
        return changeProfileButton;
    }

    public void setPreGameButton(TextButton changeProfileButton) {
        this.changeProfileButton = changeProfileButton;
    }

    public TextButton getScoreboardButton() {
        return backButton;
    }

    public void setScoreboardButton(TextButton backButton) {
        this.backButton = backButton;
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

    public TextButton getChangeUsernameButton() {
        return changeUsernameButton;
    }

    public void setChangeUsernameButton(TextButton changeUsernameButton) {
        this.changeUsernameButton = changeUsernameButton;
    }

    public TextButton getChangePasswordButton() {
        return changePasswordButton;
    }

    public void setChangePasswordButton(TextButton changePasswordButton) {
        this.changePasswordButton = changePasswordButton;
    }

    public TextButton getDeleteAccountButton() {
        return deleteAccountButton;
    }

    public void setDeleteAccountButton(TextButton deleteAccountButton) {
        this.deleteAccountButton = deleteAccountButton;
    }

    public TextButton getChangeProfileButton() {
        return changeProfileButton;
    }

    public void setChangeProfileButton(TextButton changeProfileButton) {
        this.changeProfileButton = changeProfileButton;
    }

    public TextButton getBackButton() {
        return backButton;
    }

    public void setBackButton(TextButton backButton) {
        this.backButton = backButton;
    }

    public TextField getUsername() {
        return username;
    }

    public void setUsername(TextField username) {
        this.username = username;
    }

    public TextField getPassword() {
        return password;
    }

    public void setPassword(TextField password) {
        this.password = password;
    }
}
