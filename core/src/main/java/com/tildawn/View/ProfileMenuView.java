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
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tildawn.Controller.ProfileMenuController;
import com.tildawn.Main;

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

    private final Label errorLabel;
    private final Label SuccessMessageLabel;
    private Timer.Task clearErrorTask;
    private Timer.Task clearErrorTask2;

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

        SuccessMessageLabel = new Label("", skin);
        SuccessMessageLabel.setColor(Color.GREEN);
        errorLabel = new Label("", skin);
        errorLabel.setColor(Color.RED);
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
        menuTable.add(deleteAccountButton).colspan(2).row();
        menuTable.add(changeProfileButton).colspan(2).row();
        menuTable.add(backButton).colspan(2).row();

        menuTable.add(errorLabel).colspan(2).center().row();
        menuTable.add(SuccessMessageLabel).colspan(2).center().row();

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
        controller.handleClickButton();
    }

    @Override
    public void resize(int i, int i1) { }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() {
        if (clearErrorTask != null) {
            clearErrorTask.cancel();
        }
        errorLabel.setText("");
    }

    @Override
    public void dispose() {
        stage.dispose();
    }


    public void setErrorMessage(String message) {
        errorLabel.setText(message);
        if (clearErrorTask != null) {
            clearErrorTask.cancel();
        }
        clearErrorTask = new Timer.Task() {
            @Override
            public void run() {
                errorLabel.setText("");
            }
        };
        Timer.schedule(clearErrorTask, 5);
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
    public ProfileMenuController getController() { return controller; }
    public TextButton getChangeUsernameButton() { return changeUsernameButton; }
    public void setChangeUsernameButton(TextButton changeUsernameButton) { this.changeUsernameButton = changeUsernameButton; }
    public TextButton getChangePasswordButton() { return changePasswordButton; }
    public void setChangePasswordButton(TextButton changePasswordButton) { this.changePasswordButton = changePasswordButton; }
    public TextButton getDeleteAccountButton() { return deleteAccountButton; }
    public void setDeleteAccountButton(TextButton deleteAccountButton) { this.deleteAccountButton = deleteAccountButton; }
    public TextButton getChangeProfileButton() { return changeProfileButton; }
    public void setChangeProfileButton(TextButton changeProfileButton) { this.changeProfileButton = changeProfileButton; }
    public TextButton getBackButton() { return backButton; }
    public void setBackButton(TextButton backButton) { this.backButton = backButton; }
    public TextField getUsername() { return username; }
    public void setUsername(TextField username) { this.username = username; }
    public TextField getPassword() { return password; }
    public void setPassword(TextField password) { this.password = password; }
}
